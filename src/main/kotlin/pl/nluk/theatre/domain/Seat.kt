package pl.nluk.theatre.domain

import javax.persistence.*


@Entity
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "SEAT_ID", unique = true))
)
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "SEQ_SEAT_ID", sequenceName = "SEQ_SEAT_ID", allocationSize = 1)
abstract class Seat : BaseEntity() {

    @JoinColumn(referencedColumnName = "HALL_ID")
    @Column(name = "SEAT_HALL_ID")
    var hallId : Long = 0

    @JoinColumn(referencedColumnName = "HALL_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(targetEntity = Hall::class, fetch =  FetchType.LAZY)
    var hall : Hall? = null

    @Transient
    open var size = 0

}