package pl.nluk.theatre.controller.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import pl.nluk.theatre.domain.Lounge
import pl.nluk.theatre.domain.Seat
import pl.nluk.theatre.domain.SingleSeat
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

abstract class SeatDTO{
    companion object {
        val objectMapper = ObjectMapper()
        @JvmStatic
        @JsonCreator
        fun creator(json : Map<String, Any>) : SeatDTO {
            return when{
                "row" in json -> objectMapper.convertValue(json, SingleSeatDTO::class.java)
                else -> objectMapper.convertValue(json, LoungeSeatDTO::class.java)
            }
        }
    }

    abstract fun fillProperties(seat : Seat)
}

open class SingleSeatDTO : SeatDTO() {

    @NotNull
    var row = 0

    @NotNull
    var no = 0

    override fun fillProperties(seat: Seat) {
        (seat as SingleSeat).also {
            row = it.row
            no = it.no
        }
    }
}

open class LoungeSeatDTO : SeatDTO() {
    @NotBlank
    var name = ""

    @NotNull
    var seats = 0

    override fun fillProperties(seat: Seat) {
        (seat as Lounge).also {
            name = it.name
            seats = it.seats
        }
    }
}

open class SingleSeatDetailsDTO : SingleSeatDTO(){
    @NotNull
    var id = 0L

    override fun fillProperties(seat: Seat) {
        super.fillProperties(seat)
        id = seat.id
    }

}

open class LoungeSeatDetailsDTO : LoungeSeatDTO(){
    @NotNull
    var id = 0L

    override fun fillProperties(seat: Seat) {
        super.fillProperties(seat)
        id = seat.id
    }
}

