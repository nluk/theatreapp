package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.SingleSeatDTO
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "SINGLE_SEATS")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_SINS_ID", allocationSize = 1)
@PrimaryKeyJoinColumn(name = "SINS_SEAT_ID")
class SingleSeat : Seat{

    constructor() : super()

    constructor(singleSeatDTO: SingleSeatDTO) : super(){
        singleSeatDTO.let {
            row = it.row
            no = it.no
        }
    }

    @Column(name = "SINS_ROW")
    var row = 0

    @Column(name = "SINS_NO")
    var no = 0

    @Transient
    override var size: Int = 1
}