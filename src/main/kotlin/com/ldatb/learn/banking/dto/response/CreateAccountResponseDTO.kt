package com.ldatb.learn.banking.dto.response

@JvmRecord
data class CreateAccountResponseDTO(
    val error: Boolean = false,
    val data: CreateAccountDetailsDTO,
)

data class CreateAccountDetailsDTO(
    val login: String,
    val transferKey: String,
    val secretToken: UInt,
    val firstName: String,
    val lastName: String,
    val balance: Long,
    val defaultCurrency: String
)