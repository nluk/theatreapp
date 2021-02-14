package pl.nluk.theatre.controller.dto

import java.util.*

class ConfirmationDTO : TicketDTO() {
    lateinit var reservedUntil: Date
}