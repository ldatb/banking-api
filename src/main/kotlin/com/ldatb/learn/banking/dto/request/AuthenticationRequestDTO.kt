package com.ldatb.learn.banking.dto.request

import com.ldatb.learn.banking.model.Account
import jakarta.validation.constraints.NotBlank

/**
 * A record of the data required to authenticate an account.
 * Treat this class as a JSON object
 *
 * @param login is the [Account.login] value
 * @param password is the [Account.hashedPassword] value
 */
@JvmRecord
data class AuthenticationRequestDTO(
    @NotBlank(message = "The login is required")
    val login: String,

    @NotBlank(message = "The password is required")
    val password: String
)
