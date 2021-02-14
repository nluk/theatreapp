package pl.nluk.theatre.domain

import javax.persistence.*


@Entity
@Table(name = "PRICING")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "PRIC_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_PRIC_ID", allocationSize = 1)
class Pricing : BaseEntity(){

    @JoinColumn(name ="PRIC_PLAY_ID", referencedColumnName = "PLAY_ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Play::class, fetch =  FetchType.LAZY)
    var play : Play? = null

    @JoinColumn(referencedColumnName = "PLAY_ID")
    @Column(name = "PRIC_PLAY_ID", nullable = false)
    var playId : Long = 0

    @JoinColumn(referencedColumnName = "SEAT_ID")
    @Column(name = "PRIC_SEAT_ID", nullable = true)
    var seatId : Long = 0

    @Column(name = "PRIC_VALUE")
    var price = 0.0

}