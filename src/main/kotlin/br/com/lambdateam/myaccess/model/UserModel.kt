package br.com.lambdateam.myaccess.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class UserModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "username", length = 60)
    val userName: String,
    @Column(name = "email", length = 75)
    val email: String,
    @Column(name = "password", length = 256)
    val password: String
)