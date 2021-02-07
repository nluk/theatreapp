package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.PlayDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PLAYS")
class Play {

    @Id
    @Column(name = "PL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id  = 0L

    @Column(name = "PL_DATE")
    var date = Date()

    @Column(name = "PL_TITLE")
    var title = ""

    @Column(name = "PL_AUTHOR")
    var author = ""

    @Column(name = "PL_TICKETS")
    var remainingTickets = 0L

    fun fillProperties(playDTO: PlayDTO) {
        playDTO.let {
            date = it.date
            title = it.title
            author = it.author ?: ""
            remainingTickets = it.remainingTickets
        }
    }


}
