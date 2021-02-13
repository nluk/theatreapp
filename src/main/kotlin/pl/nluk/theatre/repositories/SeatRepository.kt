package pl.nluk.theatre.repositories

import org.springframework.data.repository.CrudRepository
import pl.nluk.theatre.domain.Seat

interface SeatRepository : CrudRepository<Seat,Long>