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
    lateinit var author: String
}

class PlayCreateDTO : PlayDTO(){
    var hallId : Long = 0
}

class PlayDetailsDTO : PlayDTO() {
    lateinit var hall: HallDTO
}