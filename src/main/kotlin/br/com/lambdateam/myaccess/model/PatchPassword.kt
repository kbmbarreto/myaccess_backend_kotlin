package br.com.lambdateam.myaccess.model

data class PatchPassword(

    val id: Long?,
    val description: String?,
    val url: String?,
    val userName: String?,
    val password: String?,
    val notes: String?,
    val idUser: Long
)