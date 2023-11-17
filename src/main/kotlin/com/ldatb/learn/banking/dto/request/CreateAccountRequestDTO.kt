package com.ldatb.learn.banking.dto.request

@JvmRecord
data class CreateAccountRequestDTO(
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
