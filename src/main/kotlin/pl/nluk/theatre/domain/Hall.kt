package pl.nluk.theatre.domain

import javax.persistence.*

@Entity
@Table(name = "HALLS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "HALL_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_HALL", allocationSize = 1)
class Hall : BaseEntity() {

    @Column(name = "HALL_CODE")
    var code = ""

    @OneToMany(targetEntity = Seat::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "hall")
    var seats : Set<Seat>? = null

}