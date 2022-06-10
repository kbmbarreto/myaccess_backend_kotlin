package br.com.lambdateam.myaccess.controller

import br.com.lambdateam.myaccess.model.PatchUser
import br.com.lambdateam.myaccess.model.UserModel
import br.com.lambdateam.myaccess.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping("users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserControllerImpl(val userRepository: UserRepository) : UserController {

    @GetMapping
    override fun listUsers() = userRepository.findAll()

    @GetMapping("/{id}")
    override fun findUser(@PathVariable("id") id: Long) =
        userRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createUser(@RequestBody user: UserModel) = userRepository.save(user)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun fullUpdateUser(@PathVariable("id") id:Long, @RequestBody user:UserModel) : UserModel {
        val foundUser = findUser(id)
        val copyUser = foundUser.copy(
            userName = user.userName,
            email = user.email,
            password = user.password
        )
        return userRepository.save(copyUser)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun incrementalUpdateUser(@PathVariable("id") id: Long, @RequestBody user: PatchUser): UserModel {
        val foundUser = findUser(id)
        val copyUser = foundUser.copy(
            userName = user.userName ?: foundUser.userName,
            email = user.email ?: foundUser.email,
            password = user.password ?: foundUser.password
        )
        return userRepository.save(copyUser)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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