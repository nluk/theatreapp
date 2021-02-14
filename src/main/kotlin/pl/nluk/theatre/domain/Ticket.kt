package pl.nluk.theatre.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TICKETS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "TCKT_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_TCKT_ID", allocationSize = 1)
class Ticket : BaseEntity() {

    companion object Info {
        object Statuses {
            const val RESERVED = "R"
            const val PURCHASED = "P"
            const val AWAITING_PAYMENT = "A"
        }
    }

    @JoinColumn(referencedColumnName = "CUST_ID")
    @Column(name = "TCKT_CUST_ID", nullable = false)
    var customerId : Long = 0

    @JoinColumn(name ="TCKT_CUST_ID", referencedColumnName = "CUST_ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Customer::class, fetch =  FetchType.LAZY)
    var customer : Customer? = null

    @Column(name = "TCKT_PLAY_ID")
    var playId = 0L

    @Column(name = "TCKT_SEAT_ID")
    var seatId = 0L

    @Column(name = "TCKT_RESERVED")
    var reservedUntil : Date? = null

    @JoinColumn(referencedColumnName = "PRIC_ID")
    @Column(name = "TCKT_PRIC_ID", nullable = false)
    var pricingId : Long = 0

    @JoinColumn(name ="TCKT_PRIC_ID", referencedColumnName = "PRIC_ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Pricing::class, fetch =  FetchType.LAZY)
    var pricing : Pricing? = null

    @Column(name = "TCKT_STATUS")
    var status = ""
}