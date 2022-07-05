package br.com.lambdateam.myaccess.repository

import br.com.lambdateam.myaccess.model.PasswordModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PasswordRepository : JpaRepository<PasswordModel, Long> {

    override fun findById(id: Long): Optional<PasswordModel>

    fun getByDescriptionContaining(description: String): List<PasswordModel>

}