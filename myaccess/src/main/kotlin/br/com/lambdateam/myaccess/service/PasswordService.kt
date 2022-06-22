package br.com.lambdateam.myaccess.service

import br.com.lambdateam.myaccess.model.PasswordModel
import br.com.lambdateam.myaccess.repository.PasswordRepository
import org.springframework.stereotype.Service

@Service
class PasswordService (private val passwordRepository: PasswordRepository){

    fun findAll(): List<PasswordModel> {
        return passwordRepository.findAll().toList()
    }

    fun getByDescription(description: String?): List<PasswordModel> {
        description?.let {
            return passwordRepository.getByDescriptionContaining(it)
        }
        return passwordRepository.findAll().toList()
    }
}