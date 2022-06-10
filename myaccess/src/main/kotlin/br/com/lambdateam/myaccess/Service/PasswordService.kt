package br.com.lambdateam.myaccess.Service

import br.com.lambdateam.myaccess.model.PasswordModel
import br.com.lambdateam.myaccess.repository.PasswordRepository
import org.springframework.stereotype.Component

@Component
class PasswordService(val passwordRepository: PasswordRepository) {

    fun savePasssword(password: PasswordModel) = passwordRepository.save(password)
}