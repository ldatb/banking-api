package com.ldatb.learn.banking.dto.request

import com.ldatb.learn.banking.model.Account

/**
 * A record of the data required to create an account.
 * Treat this class as a JSON object
 *
 * @param login is the [Account.login] value
 * @param password is the [Account.hashedPassword] value
 * @param firstName is the [Account.firstName] value
 * @param lastName is the [Account.lastName] value
 */
@JvmRecord
data class CreateAccountRequestDTO(
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
