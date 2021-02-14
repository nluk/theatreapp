package pl.nluk.theatre.errors

import org.springframework.http.HttpStatus

enum class ErrorType {
    UNKNOWN_ERROR("Unknown error occured"),
    UNKNOWN_DTO("Unknown DTO {}"),
    UNKNOWN_PLAY("Play with id {} doesn't exist", HttpStatus.NOT_FOUND),
    UNKNOWN_SEAT("Seat with id {} doesn't exist", HttpStatus.NOT_FOUND),
    UNKNOWN_HALL("Hall with id {} doesn't exist", HttpStatus.NOT_FOUND),
    UNKNOWN_TICKET("Ticket with id {} doesn't exist", HttpStatus.NOT_FOUND),
    UNKNOWN_CUSTOMER("Customer with id {} doesn't exist", HttpStatus.NOT_FOUND);

    private val messageTemplate : String
    val status : HttpStatus

    constructor(messageTemplate : String){
        this.messageTemplate = messageTemplate
        this.status = HttpStatus.INTERNAL_SERVER_ERROR
    }

    constructor(messageTemplate: String, status : HttpStatus){
        this.messageTemplate = messageTemplate
        this.status = status
    }

    fun message(vararg args : Any) : String {
        val rawTemplate = messageTemplate.split("{}")
        return rawTemplate.reduceIndexed{ index, acc, next -> if(index != rawTemplate.size) acc + args[index - 1] + next else acc + next }
    }
}