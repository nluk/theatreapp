package pl.nluk.theatre.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.nluk.theatre.controller.dto.*
import pl.nluk.theatre.domain.Seat
import pl.nluk.theatre.domain.SingleSeat
import pl.nluk.theatre.service.HallFacade

@RestController
@RequestMapping("/halls")
class HallController(
    @Autowired
    val hallFacade: HallFacade
) {

    @PostMapping("/create")
    fun createHall(@RequestBody hallDTO: HallDTO) : ResponseEntity<HallUpdateDTO>{
        val hall = hallFacade.createHall(hallDTO)
        val responseDTO = HallUpdateDTO()
        responseDTO.apply {
            seats = hall.seats?.map{ seatToDTO(it) }
            id = hall.id
            code = hall.code
        }
        return ResponseEntity.ok(responseDTO)
    }

    private fun seatToDTO(seat : Seat) : SeatDTO{
        val seatDTO = if(seat is SingleSeat) SingleSeatDetailsDTO() else LoungeSeatDetailsDTO()
        seatDTO.fillProperties(seat)
        return seatDTO
    }

}