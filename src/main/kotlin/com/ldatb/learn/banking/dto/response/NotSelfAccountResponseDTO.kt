package com.ldatb.learn.banking.dto.response

import com.ldatb.learn.banking.model.Account

/**
 * A record of the response data regarding information about an Account that doesn't belong
 * to the user requesting the information (third-party person).
 * Treat this class as a JSON object
 *
 * @param error is always false and is required due to the architecture of the API
 * @param data is an instance of [AccountResponseDetailsDTO]
 */
data class NotSelfAccountResponseDTO(
    val error: Boolean = false,
    val data: NotSelfAccountResponseDetailsDTO
)

/**
 * The response data regarding information about an Account that doesn't belong
 * to the user requesting the information.
 * Treat this class as a JSON object
 *
 * @param transferKey is the [Account.transferKey]
 * @param firstName is the [Account.firstName]
 * @param lastName is the [Account.lastName]
 */
data class NotSelfAccountResponseDetailsDTO(
    val transferKey: String,
    val firstName: String,
    val lastName: String
)
