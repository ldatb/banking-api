package com.ldatb.learn.banking.dto.request

@JvmRecord
data class CreateAccountDTO(
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val defaultCurrency: String,
)