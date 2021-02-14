package pl.nluk.theatre.controller.dto

import javax.validation.constraints.NotNull

open class TicketDTO {
    @NotNull
    var customerId = 0L

    @NotNull
    var playId = 0L

    @NotNull
    var seatId = 0L
}