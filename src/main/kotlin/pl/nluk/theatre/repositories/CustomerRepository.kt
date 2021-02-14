package pl.nluk.theatre.repositories

import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.nluk.theatre.domain.Customer
import java.util.*
import javax.persistence.LockModeType

interface CustomerRepository : CrudRepository<Customer, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Customer c WHERE c.id = :id")
    fun lockCustomerById(@Param("id") id : Long) : Optional<Customer>
}