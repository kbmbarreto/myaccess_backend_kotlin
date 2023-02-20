package br.com.lambdateam.myaccess.exceptions

class AuthenticationException(override val message: String, val errorCode: String) : Exception()