package pl.nluk.theatre.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

open class PlayDTO {
    @NotBlank
    val title: String = ""

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    lateinit var date: Date

    @NotBlank
    var author: String = ""

    @NotNull
    var hallId : Long = 0
}

open class PlayUpdateDTO : PlayDTO(){
    @NotNull
    var id = 0L
}

class PlayDetailsDTO : PlayUpdateDTO() {
    var hall: HallDTO? = null
    var remainingSeats : List<Pair<String, Int>>? = null
}