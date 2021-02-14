package pl.nluk.theatre.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.nluk.theatre.domain.Pricing

interface PricingRepository : CrudRepository<Pricing, Long> {
    @Query("SELECT p FROM Pricing p WHERE p.playId = :playId AND (p.seatId = :seatId OR p.seatId IS NULL)")
    fun findApplicablePricings(@Param("playId") playId : Long, @Param("seatId") seatId : Long) : Set<Pricing>
}