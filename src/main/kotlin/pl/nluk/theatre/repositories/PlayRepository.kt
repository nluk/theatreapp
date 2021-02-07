package pl.nluk.theatre.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import pl.nluk.theatre.domain.Play

interface PlayRepository : CrudRepository<Play, Long> {
}