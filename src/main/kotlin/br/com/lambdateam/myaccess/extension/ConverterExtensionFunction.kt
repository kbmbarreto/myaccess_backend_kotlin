package br.com.lambdateam.myaccess.extension

import br.com.lambdateam.myaccess.model.*

fun PostPassword.toModel(userModel: UserModel): PasswordModel {
    return PasswordModel(
        description = description,
        url = url,
        userName = userName,
        password = password,
        notes = notes,
        user = userModel
    )
}

fun PasswordModel.toResponse(): PasswordResponse {
    return PasswordResponse(
        id = this.id,
        description = this.description,
        url = url,
        userName = userName,
        password = password,
        notes = notes
//        userId = user.id
    )
}

fun PostUser.toModel(): UserModel {
    return UserModel(
        userName = userName,
        email = email,
        password = password
    )
}