package pl.nluk.theatre.repositories

import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.nluk.theatre.domain.Customer
import pl.nluk.theatre.domain.Ticket
import java.util.*
import javax.persistence.LockModeType

interface TicketRepository : CrudRepository<Ticket, Long> {

    fun getTicketBySeatIdAndPlayId(seatId : Long, playId: Long) : Ticket?


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM Ticket t WHERE t.id = :id")
    fun lockTicketById(@Param("id") id : Long) : Optional<Ticket>


}