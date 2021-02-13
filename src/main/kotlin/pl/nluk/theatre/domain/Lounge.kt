package pl.nluk.theatre.domain

import javax.persistence.*

@Entity
@Table(name = "LOUNGES")
@SequenceGenerator(name = "SEQ_LNGE_ID", sequenceName = "SEQ_LNGE_ID", allocationSize = 1)
class Lounge : Seat() {

    @Column(name = "LNGE_NAME")
    var name = ""

    @Column(name = "LNGE_SEATS")
    var seats = 0

    override var size: Int
        get() = seats
        set(value) { seats = value }
}