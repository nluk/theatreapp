package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.HallDTO
import javax.persistence.*

@Entity
@Table(name = "HALLS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "HALL_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_HALL_ID", allocationSize = 1)
class Hall : BaseEntity() {

    @Column(name = "HALL_CODE")
    var code = ""

    @OneToMany(targetEntity = Seat::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "hall")
    var seats : Set<Seat>? = null

    @OneToMany(targetEntity = Play::class, fetch = FetchType.LAZY, mappedBy = "hall")
    var plays : Set<Play>? = null

    fun fillProperties(hallDTO: HallDTO){
        code = hallDTO.code
    }
}