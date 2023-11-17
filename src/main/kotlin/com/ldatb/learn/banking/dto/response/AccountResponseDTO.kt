package com.ldatb.learn.banking.dto.response

@JvmRecord
data class AccountResponseDTO(
    val error: Boolean = false,
    val data: AccountResponseDetailsDTO,
)

data class AccountResponseDetailsDTO(
    val login: String,
    val transferKey: String,
    val secretToken: UInt,
    val firstName: String,
    val lastName: String,
    val balance: Long,
    val defaultCurrency: String
)