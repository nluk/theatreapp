package pl.nluk.theatre.domain

import pl.nluk.theatre.controller.dto.LoungeSeatDTO
import pl.nluk.theatre.controller.dto.SeatDTO
import pl.nluk.theatre.controller.dto.SingleSeatDTO
import pl.nluk.theatre.errors.AppException
import pl.nluk.theatre.errors.ErrorType
import javax.persistence.*


@Entity
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "SEAT_ID", unique = true))
)
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_SEAT_ID", allocationSize = 1)
abstract class Seat : BaseEntity() {

    companion object {
        fun fromDTO(dto : SeatDTO) : Seat{
            return when(dto){
                is SingleSeatDTO -> SingleSeat(dto)
                is LoungeSeatDTO -> Lounge(dto)
                else -> throw AppException(ErrorType.UNKNOWN_DTO, dto::class)
            }
        }
    }

    @JoinColumn(referencedColumnName = "HALL_ID")
    @Column(name = "SEAT_HALL_ID", nullable = false)
    var hallId : Long = 0

    @JoinColumn(name ="SEAT_HALL_ID", referencedColumnName = "HALL_ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Hall::class, fetch =  FetchType.LAZY)
    var hall : Hall? = null

    @Transient
    var size = 0

}