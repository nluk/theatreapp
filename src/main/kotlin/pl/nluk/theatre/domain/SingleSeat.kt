package pl.nluk.theatre.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "SINGLE_SEATS")
@SequenceGenerator(name = "SEQ_SINS_ID", sequenceName = "SEQ_SINS_ID", allocationSize = 1)
class SingleSeat : Seat() {

    @Column(name = "SINS_ROW")
    var row = 0

    @Column(name = "SINS_NO")
    var no = 0

    override var size: Int = 1
}