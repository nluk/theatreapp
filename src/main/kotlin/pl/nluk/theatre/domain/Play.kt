package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.PlayDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PLAYS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "PLAY_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_PLAY_ID", sequenceName = "SEQ_PLAY_ID", allocationSize = 1)
class Play : BaseEntity() {

    @Column(name = "PLAY_DATE")
    var date = Date()

    @Column(name = "PLAY_TITLE")
    var title = ""

    @Column(name = "PLAY_AUTHOR")
    var author = ""

    @Column(name = "PLAY_TICKETS")
    var remainingTickets = 0L

}
