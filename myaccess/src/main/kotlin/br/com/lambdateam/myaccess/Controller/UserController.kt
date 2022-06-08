package br.com.lambdateam.myaccess.Controller

import br.com.lambdateam.myaccess.model.PatchUser
import br.com.lambdateam.myaccess.model.UserModel
import br.com.lambdateam.myaccess.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping("users")
class UserControllerImpl(val userRepository: UserRepository) : UserController {

    @GetMapping
    override fun listUsers() = userRepository.findAll()

    @GetMapping("/{id}")
    override fun findUser(@PathVariable("id") id: Long) =
        userRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    override fun createUser(@RequestBody user: UserModel) = userRepository.save(user)

    @PutMapping("/{id}")
    override fun fullUpdateUser(@PathVariable("id") id:Long, @RequestBody user:UserModel) : UserModel {
        val foundUser = findUser(id)
        val copyUser = foundUser.copy(
            username = user.username,
            email = user.email,
            password = user.password
        )
        return userRepository.save(copyUser)
    }

    @PatchMapping("/{id}")
    override fun incrementalUpdateUser(@PathVariable("id") id: Long, @RequestBody user: PatchUser): UserModel {
        val foundUser = findUser(id)
        val copyUser = foundUser.copy(
            username = user.username ?: foundUser.username,
            email = user.email ?: foundUser.email,
            password = user.password ?: foundUser.password
        )
        return userRepository.save(copyUser)
    }

    @DeleteMapping("/{id}")
    override fun deleteUser(@PathVariable("id") id: Long) = userRepository.delete(findUser(id))
}

interface UserController {

    fun listUsers() : List<UserModel>

    fun findUser(id: Long) : UserModel

    fun createUser(user: UserModel): UserModel

    fun fullUpdateUser(id: Long, user: UserModel) : UserModel

    fun incrementalUpdateUser(id: Long, user: PatchUser) : UserModel

    fun deleteUser(@PathVariable("id") id: Long)
}