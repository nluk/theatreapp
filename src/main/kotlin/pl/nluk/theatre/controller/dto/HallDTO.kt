package pl.nluk.theatre.controller.dto

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

open class HallDTO {
    @NotBlank
    var code = ""

    @NotNull
    var seats : List<@Valid SeatDTO>? = null
}

open class HallUpdateDTO : HallDTO(){
    @NotNull
    var id = 0L
}
