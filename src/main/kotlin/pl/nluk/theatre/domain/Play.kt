package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.PlayDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PLAYS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "PLAY_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_PLAY_ID", allocationSize = 1)
class Play : BaseEntity() {

    @Column(name = "PLAY_DATE")
    var date = Date()

    @Column(name = "PLAY_TITLE")
    var title = ""

    @Column(name = "PLAY_AUTHOR")
    var author = ""

    @JoinColumn(referencedColumnName = "HALL_ID")
    @Column(name = "PLAY_HALL_ID")
    var hallId = 0L

    @JoinColumn(referencedColumnName = "HALL_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(targetEntity = Hall::class, fetch =  FetchType.LAZY)
    var hall : Hall? = null

    fun fillProperties(playDTO: PlayDTO) {
        playDTO.let {
            date = it.date
            title = it.title
            author = it.author
            hallId = it.hallId
        }
    }

}
