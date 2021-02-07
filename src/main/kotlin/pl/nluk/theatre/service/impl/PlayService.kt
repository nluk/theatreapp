package pl.nluk.theatre.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.nluk.theatre.controller.dto.PlayDTO
import pl.nluk.theatre.domain.Play
import pl.nluk.theatre.errors.AppException
import pl.nluk.theatre.errors.ErrorType
import pl.nluk.theatre.repositories.PlayRepository
import pl.nluk.theatre.service.PlayFacade

@Service
class PlayService(
    @Autowired
    val playRepository: PlayRepository
) : PlayFacade {

    override fun createPlay(playDTO: PlayDTO): Play {
        val play = Play()
        play.fillProperties(playDTO)
        return playRepository.save(play)
    }

    override fun updatePlay(playDTO: PlayDTO, id : Long): Play {
        val play = playRepository.findById(id).orElseThrow{ AppException(ErrorType.UNKNOWN_PLAY, id) }
        play.fillProperties(playDTO)
        return playRepository.save(play)
    }
}