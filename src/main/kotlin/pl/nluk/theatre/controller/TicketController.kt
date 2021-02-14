package pl.nluk.theatre.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.nluk.theatre.controller.dto.ConfirmationDTO
import pl.nluk.theatre.controller.dto.TicketDTO
import pl.nluk.theatre.domain.Success
import pl.nluk.theatre.service.TicketFacade
import pl.nluk.theatre.service.impl.TicketService
import java.util.*

@RestController
@RequestMapping("/tickets")
class TicketController(
    @Autowired
    val ticketFacade: TicketFacade
) {

    @PostMapping("/reserve")
    fun reserveTicket(@RequestBody reservation: TicketDTO) : ResponseEntity<*>{
        val result = ticketFacade.reserveTicket(reservation)
        return when(result){
            is Success<Date> -> ResponseEntity.ok(prepareConfirmation(reservation, result.data))
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result)
        }
    }

    private fun prepareConfirmation(reservation: TicketDTO, reservedUntil : Date): ConfirmationDTO{
        val confirmation = ConfirmationDTO()
        confirmation.apply {
            customerId = reservation.customerId
            playId = reservation.playId
            seatId = reservation.seatId
            this.reservedUntil = reservedUntil
        }
        return confirmation
    }

}