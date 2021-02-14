package pl.nluk.theatre.service

import org.springframework.stereotype.Service
import pl.nluk.theatre.controller.dto.TicketDTO
import pl.nluk.theatre.domain.OperationResult
import java.util.*

@Service
interface TicketFacade {
    fun reserveTicket(reservation: TicketDTO) : OperationResult<Date>

    fun purchaseTicket(purchase: TicketDTO) : OperationResult<String>

    fun paymentConfirmation(token : String, ticketId: Long)
}