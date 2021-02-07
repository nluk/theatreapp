package pl.nluk.theatre.service

import org.springframework.stereotype.Service
import pl.nluk.theatre.controller.dto.PlayDTO
import pl.nluk.theatre.domain.Play

@Service
interface PlayFacade {
    fun createPlay(playDTO: PlayDTO) : Play
    fun updatePlay(playDTO: PlayDTO, id : Long) : Play
}