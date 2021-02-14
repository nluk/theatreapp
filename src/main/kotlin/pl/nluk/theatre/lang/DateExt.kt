package pl.nluk.theatre.lang

import java.time.ZoneId
import java.util.*

infix fun Date.plusMinutes(minutes : Long) : Date{
    val localDateTime = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    localDateTime.plusMinutes(minutes)
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}