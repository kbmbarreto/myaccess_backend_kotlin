package br.com.lambdateam.myaccess

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
class MyaccessApplication

fun main(args: Array<String>) {
	runApplication<MyaccessApplication>(*args)
}

@PostConstruct
fun init() {
	TimeZone.setDefault(TimeZone.getTimeZone("UTC-3:00"))
}