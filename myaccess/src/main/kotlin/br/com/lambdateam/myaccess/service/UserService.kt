package br.com.lambdateam.myaccess.service

import br.com.lambdateam.myaccess.model.UserModel
import br.com.lambdateam.myaccess.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun createUser(user: UserModel) {
        userRepository.save(user)
    }
}