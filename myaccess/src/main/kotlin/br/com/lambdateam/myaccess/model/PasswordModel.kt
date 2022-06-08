package br.com.lambdateam.myaccess.model

import javax.persistence.*

@Entity
@Table(name = "passwords")
data class PasswordModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "description", length = 128)
    val description: String,
    @Column(name = "url", length = 128)
    val url: String,
    @Column(name = "user", length = 64)
    val user: String,
    @Column(name = "password", length = 128)
    val password: String,
    @Column(name = "notes", length = 128)
    val notes: String,
    @ManyToOne
    @JoinColumn(name = "iduser")
    val iduser: UserModel
)