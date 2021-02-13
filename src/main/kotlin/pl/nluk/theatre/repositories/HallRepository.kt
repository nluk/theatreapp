package pl.nluk.theatre.repositories

import org.springframework.data.repository.CrudRepository
import pl.nluk.theatre.domain.Hall

interface HallRepository : CrudRepository<Hall, Long> {
}