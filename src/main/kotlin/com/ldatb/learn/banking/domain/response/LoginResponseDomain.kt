package com.ldatb.learn.banking.domain.response

/**
 * A record of the response data regarding information about an Account's authentication
 * Treat this class as a JSON object
 *
 * @param error is always false and is required due to the architecture of the API
 * @param data is an instance of [LoginResponseDataDTO]
 */
@JvmRecord
data class LoginResponseDTO(
    val error: Boolean = false,
    val data: LoginResponseDataDTO
)

/**
 * A response data regarding information about an Account's authentication
 * Treat this class as a JSON object
 *
 * @param token is the JWT token for the account
 */
@JvmRecord
data class LoginResponseDataDTO(
    val token: String
)
