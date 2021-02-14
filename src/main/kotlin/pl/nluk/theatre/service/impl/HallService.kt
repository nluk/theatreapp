package pl.nluk.theatre.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.nluk.theatre.controller.dto.HallDTO
import pl.nluk.theatre.controller.dto.HallUpdateDTO
import pl.nluk.theatre.domain.Hall
import pl.nluk.theatre.domain.Seat
import pl.nluk.theatre.errors.AppException
import pl.nluk.theatre.errors.ErrorType
import pl.nluk.theatre.repositories.HallRepository
import pl.nluk.theatre.repositories.SeatRepository
import pl.nluk.theatre.service.HallFacade

@Service
@Transactional
class HallService(
    @Autowired
    val hallRepository: HallRepository,
    @Autowired
    val seatRepository: SeatRepository
) : HallFacade
{
    override fun createHall(hallDTO: HallDTO): Hall {
        val hall = Hall()
        hall.fillProperties(hallDTO)
        hallRepository.save(hall)
        val seats = hallDTO.seats?.run{ map(Seat.Companion::fromDTO).toSet() } ?: emptySet()
        seats.forEach { it.hallId = hall.id }
        seatRepository.saveAll(seats)
        hall.seats = seats
        return hall
    }

    override fun updateHall(hallDTO: HallUpdateDTO): Hall {
        val hall = findHall(hallDTO.id)
        hall.fillProperties(hallDTO)
        return hallRepository.save(hall)
    }

    override fun findHall(id : Long) : Hall{
      return hallRepository.findById(id).orElseThrow { AppException(ErrorType.UNKNOWN_HALL, id) }
    }

}