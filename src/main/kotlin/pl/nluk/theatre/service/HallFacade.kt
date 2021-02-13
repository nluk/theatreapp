package pl.nluk.theatre.service

import pl.nluk.theatre.controller.dto.HallDTO
import pl.nluk.theatre.domain.Hall

interface HallFacade {
 fun createHall(hallDTO: HallDTO) : Hall
 fun updateHall(hallDTO: HallDTO) : Hall
}