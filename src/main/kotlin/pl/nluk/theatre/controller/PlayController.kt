package pl.nluk.theatre.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.nluk.theatre.controller.dto.PlayDTO
import pl.nluk.theatre.service.PlayFacade
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/plays")
class PlayController(
    @Autowired
    val playFacade: PlayFacade
) {

    @PostMapping("/create")
    fun createPlay(@RequestBody play : PlayDTO) : ResponseEntity<*>{
        return ResponseEntity.ok(playFacade.createPlay(play))
    }

    @PostMapping("/{playId}/update")
    fun updatePlay(@RequestBody play : PlayDTO, @NotNull @PathVariable("playId") id : Long) : ResponseEntity<*>{
        return ResponseEntity.ok(playFacade.updatePlay(play, id))
    }


}