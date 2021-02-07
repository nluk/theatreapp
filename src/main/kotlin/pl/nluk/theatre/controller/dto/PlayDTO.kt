package pl.nluk.theatre.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PlayDTO(
    @NotBlank
    val title : String,
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val date : Date,
    val author : String?,
    @NotNull
    val remainingTickets : Long
)

