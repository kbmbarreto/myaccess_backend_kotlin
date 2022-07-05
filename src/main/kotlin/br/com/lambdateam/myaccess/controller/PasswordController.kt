package br.com.lambdateam.myaccess.controller

import br.com.lambdateam.myaccess.extension.toModel
import br.com.lambdateam.myaccess.extension.toResponse
import br.com.lambdateam.myaccess.model.*
import br.com.lambdateam.myaccess.repository.PasswordRepository
import br.com.lambdateam.myaccess.repository.UserRepository
import br.com.lambdateam.myaccess.service.PasswordService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping("passwords")
class PasswordControllerImpl(val passwordRepository: PasswordRepository,
                             val userRepository: UserRepository,
                             val passwordService: PasswordService) : PasswordController {

    @GetMapping
    fun findAll(): List<PasswordResponse> {
        return passwordService.findAll().map { it.toResponse() }
    }

    @GetMapping("/byDescription")
    fun getByDescription(@RequestParam description: String?): List<PasswordResponse> {
        return passwordService.getByDescription(description).map { it.toResponse() }
    }

//    @GetMapping("/{id}")
//    fun findPassword(@PathVariable("id") id: Long) =
//        passwordRepository.findById(id)
//            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): PasswordModel {
        return passwordService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createPassword(@RequestBody password: PostPassword) {
        val user = userRepository.findById(password.idUser).get()
        passwordRepository.save(password.toModel(user))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun fullUpdatePassword(@PathVariable("id") id: Long, @RequestBody password: PutPassword) : PasswordModel {
        val foundPassword = findById(id)
        val copyPassword = foundPassword.copy(
            description = password.description,
            url = password.url,
            userName = password.userName,
            password = password.password,
            notes = password.notes,
            user = foundPassword.user
        )
        return passwordRepository.save(copyPassword)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun incrementalUpdatePassword(@PathVariable("id") id: Long, @RequestBody password: PatchPassword): PasswordModel {
        val foundPassword = findById(id)
        val copyPassword = foundPassword.copy(
            description = password.description ?: foundPassword.description,
            url = password.url ?: foundPassword.url,
            userName = password.userName ?: foundPassword.userName,
            password = password.password ?: foundPassword.password,
            notes = password.notes ?: foundPassword.notes,
            user = foundPassword.user
        )
        return passwordRepository.save(copyPassword)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deletePassword(@PathVariable("id") id: Long) = passwordRepository.delete(findById(id))
}

interface PasswordController {

    fun createPassword(password: PostPassword)

    fun fullUpdatePassword(id: Long, user: PutPassword) : PasswordModel

    fun incrementalUpdatePassword(id: Long, password: PatchPassword) : PasswordModel

    fun deletePassword(@PathVariable("id") id: Long)
}