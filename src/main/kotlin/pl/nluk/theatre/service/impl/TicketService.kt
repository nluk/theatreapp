package pl.nluk.theatre.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.nluk.theatre.controller.dto.TicketDTO
import pl.nluk.theatre.domain.*
import pl.nluk.theatre.errors.AppException
import pl.nluk.theatre.errors.ErrorType
import pl.nluk.theatre.lang.plusMinutes
import pl.nluk.theatre.repositories.*
import pl.nluk.theatre.service.TicketFacade
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

import kotlin.math.E

@Service
@Transactional
class TicketService(
    @Autowired
    val ticketRepository: TicketRepository,
    @Autowired
    val customerRepository: CustomerRepository,
    @Autowired
    val pricingRepository: PricingRepository,
    @Autowired
    val seatRepository: SeatRepository,
    @Autowired
    val playRepository: PlayRepository
) : TicketFacade {

    override fun reserveTicket(reservation: TicketDTO): OperationResult<Date> {
        val customer = customerRepository.lockCustomerById(reservation.customerId).orElseThrow{ AppException(ErrorType.UNKNOWN_CUSTOMER, reservation.customerId) }
        checkPlayAndSeatIds(reservation.playId, reservation.seatId)
        val now = Date()
        val reservationEnd = now plusMinutes 5
        if(customer.reservations > 3){
            return Error("Only 3 reservations allowed")
        }
        var ticket = ticketRepository.getTicketBySeatIdAndPlayId(reservation.seatId, reservation.playId)
        if(ticket != null){
            if(Ticket.Info.Statuses.PURCHASED == ticket.status){
                return Error("Ticket is no longer available")
            }
            if(ticket.reservedUntil?.after(now) == true){
                return Error("Ticket is reserved until ${ticket.reservedUntil}")
            }
            ticket.reservedUntil = reservationEnd
            ticket.customerId = reservation.customerId
            ticket.status = Ticket.Info.Statuses.RESERVED
        }
        else{
            ticket = Ticket()
            ticket.apply{
                customerId = reservation.customerId
                playId = reservation.playId
                seatId = reservation.seatId
                reservedUntil = reservationEnd
                pricing = selectPricing(reservation.playId, reservation.seatId)
                status = Ticket.Info.Statuses.RESERVED
            }
            ticketRepository.save(ticket)
        }
        customer.reservations += 1
        return Success(reservationEnd)
    }

    override fun purchaseTicket(purchase: TicketDTO): OperationResult<String> {
        val customer = customerRepository.lockCustomerById(purchase.customerId).orElseThrow{ AppException(ErrorType.UNKNOWN_CUSTOMER, purchase.customerId) }
        checkPlayAndSeatIds(purchase.playId, purchase.seatId)
        val ticket = ticketRepository.getTicketBySeatIdAndPlayId(purchase.seatId, purchase.playId)
        val now = Date()
        if(ticket == null) {
            return Error("Place a reservation first")
        }
        if(Ticket.Info.Statuses.PURCHASED == ticket.status){
            return Error(if(ticket.customerId == purchase.customerId) "You already bought this ticket" else "Ticket is no longer available")
        }
        if(ticket.reservedUntil?.after(now) == true && ticket.customerId != purchase.customerId){
            return Error("Ticket is reserved until ${ticket.reservedUntil}")
        }
        ticket.reservedUntil = now plusMinutes 15
        ticket.status = Ticket.Info.Statuses.AWAITING_PAYMENT
        return Success(generatePaymentURI(ticket.id))
    }

    override fun paymentConfirmation(token : String, ticketId: Long){
        val ticket = ticketRepository.lockTicketById(ticketId).orElseThrow { AppException(ErrorType.UNKNOWN_TICKET, ticketId) }
        if(ticket.reservedUntil?.after(Date()) == false){
            //revertPayment
        }
        ticket.status = Ticket.Info.Statuses.PURCHASED
    }

    fun selectPricing(playId : Long, seatId : Long) : Pricing{
        val pricings = pricingRepository.findApplicablePricings(playId, seatId)
        return when(pricings.size){
            1 -> pricings.first()
            else -> pricings.first { it.seatId == seatId }
        }
    }

    fun generatePaymentURI(ticketId : Long) = "https://www.somepaymentgateway.com/pay?token=mytoken&trn=$ticketId"

    fun checkPlayAndSeatIds(playId: Long, seatId: Long){
        if(!seatRepository.existsById(seatId)){
            throw AppException(ErrorType.UNKNOWN_SEAT, playId)
        }
        if(!playRepository.existsById(playId)){
            throw AppException(ErrorType.UNKNOWN_PLAY, playId)
        }
    }
}