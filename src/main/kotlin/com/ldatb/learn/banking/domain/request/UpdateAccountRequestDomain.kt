package com.ldatb.learn.banking.domain.request

import com.ldatb.learn.banking.model.Account
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

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
data class UpdateAccountRequestDomain(
    @Size(min = 2, max = 64, message = "The length of the login must be between 2 and 100 characters.")
    val login: String?,

    @Size(min = 10, message = "The transfer key must have at least 10 characters")
    val transferKey: String?,

    @Size(min = 8, message = "The password must have at least 8 characters.")
    val password: String?,

    @Size(min = 6, max = 6, message = "The secret token must be a 6-digit number")
    val secretToken: UInt?,

    @Size(min = 2, max = 100, message = "The length of the first name must be between 2 and 100 characters.")
    val firstName: String?,

    @NotBlank(message = "The lastName is required")
    @Size(min = 2, max = 100, message = "The length of the last name must be between 2 and 100 characters.")
    val lastName: String?
)
