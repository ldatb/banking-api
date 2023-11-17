package com.ldatb.learn.banking.dto.request

@JvmRecord
data class AuthenticationRequestDTO(
    val login: String,
    val password: String
)