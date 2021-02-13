package pl.nluk.theatre.domain

import javax.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id : Long = 0
}