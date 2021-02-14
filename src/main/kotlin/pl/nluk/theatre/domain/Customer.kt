package pl.nluk.theatre.domain

import javax.persistence.*


@Entity
@Table(name = "CUSTOMERS")
@AttributeOverrides(
    AttributeOverride(name = "id", column = Column(name = "CUST_ID", unique = true))
)
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_CUST_ID", allocationSize = 1)
class Customer : BaseEntity(){

    @Column(name = "CUST_LOGIN", unique = true)
    var login = ""

    @Column(name = "CUST_PASS", nullable = false)
    var passwordHash = ""

    @Column(name = "CUST_RSRV")
    var reservations = 0

}