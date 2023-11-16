package com.ldatb.learn.banking.dto.request


@JvmRecord
data class AuthenticationDTO(
    val login: String,
    val password: String
)