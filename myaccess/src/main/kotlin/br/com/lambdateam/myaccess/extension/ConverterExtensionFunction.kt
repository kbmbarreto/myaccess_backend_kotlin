package br.com.lambdateam.myaccess.extension

import br.com.lambdateam.myaccess.model.PasswordModel
import br.com.lambdateam.myaccess.model.PostPassword
import br.com.lambdateam.myaccess.model.PostUser
import br.com.lambdateam.myaccess.model.UserModel

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

fun PostUser.toModel(userModel: UserModel): UserModel {
    return UserModel(
        userName = userName,
        email = email,
        password = password
    )
}