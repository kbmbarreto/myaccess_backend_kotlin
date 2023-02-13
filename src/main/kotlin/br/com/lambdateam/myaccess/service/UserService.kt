package br.com.lambdateam.myaccess.service

import br.com.lambdateam.myaccess.enums.Profile
import br.com.lambdateam.myaccess.model.UserModel
import br.com.lambdateam.myaccess.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository, private val bCrypt: BCryptPasswordEncoder){

    fun createUser(user: UserModel) {
        val userCopy = user.copy(
            roles = setOf(Profile.USER),
            password = bCrypt.encode(user.password)
        )
        userRepository.save(userCopy)
    }
}