package com.ldatb.learn.banking.dto.response

import com.ldatb.learn.banking.model.Account

/**
 * A record of the response data regarding information about an Account
 * Treat this class as a JSON object
 *
 * @param error is always false and is required due to the architecture of the API
 * @param data is an instance of [AccountResponseDetailsDTO]
 */
@JvmRecord
data class AccountResponseDTO(
    val error: Boolean = false,
    val data: AccountResponseDetailsDTO
)

/**
 * The response data regarding information about an Account
 * Treat this class as a JSON object
 *
 * @param login is the [Account.login]
 * @param transferKey is the [Account.transferKey]
 * @param secretToken is the [Account.secretToken]
 * @param firstName is the [Account.firstName]
 * @param lastName is the [Account.lastName]
 * @param balance is the [Account.balance]
 * @param defaultCurrency is the [Account.defaultCurrency]
 */
data class AccountResponseDetailsDTO(
    val login: String,
    val transferKey: String,
    val secretToken: UInt,
    val firstName: String,
    val lastName: String,
    val balance: Long,
    val defaultCurrency: String
)
