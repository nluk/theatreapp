package pl.nluk.theatre.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.nluk.theatre.controller.dto.PlayDTO
import pl.nluk.theatre.controller.dto.PlayDetailsDTO
import pl.nluk.theatre.domain.Play
import pl.nluk.theatre.errors.AppException
import pl.nluk.theatre.errors.ErrorType
import pl.nluk.theatre.repositories.HallRepository
import pl.nluk.theatre.repositories.PlayRepository
import pl.nluk.theatre.service.HallFacade
import pl.nluk.theatre.service.PlayFacade
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Service
@Transactional
class PlayService(
    @Autowired
    val playRepository: PlayRepository,
    @Autowired
    val hallFacade: HallFacade
) : PlayFacade {

    override fun createPlay(playDTO: PlayDTO): Play {
        val play = Play()
        play.hall = hallFacade.findHall(playDTO.hallId)
        play.fillProperties(playDTO)
        playRepository.save(play)
        return play
    }

    override fun updatePlay(playDTO: PlayDTO, id : Long): Play {
        val play = playRepository.findById(id).orElseThrow{ AppException(ErrorType.UNKNOWN_PLAY, id) }
        play.fillProperties(playDTO)
        return playRepository.save(play)
    }
}