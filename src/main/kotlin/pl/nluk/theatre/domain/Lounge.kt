package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.LoungeSeatDTO
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "LOUNGES")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_LNGE_ID", allocationSize = 1)
@PrimaryKeyJoinColumn(name = "LNGE_SEAT_ID")
class Lounge : Seat{

    constructor() : super()

    constructor(loungeSeatDTO: LoungeSeatDTO) : super(){
        loungeSeatDTO.let {
            name = it.name
            seats = it.seats
        }
    }

    @Column(name = "LNGE_NAME")
    var name = ""

    @Column(name = "LNGE_SEATS")
    var seats = 0

    override var size: Int
        get() = seats
        set(value) { seats = value }
}