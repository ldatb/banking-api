package com.ldatb.learn.banking.dto.response

@JvmRecord
data class LoginResponseDTO(
    val error: Boolean = false,
    val data: LoginResponseDataDTO
)

@JvmRecord
data class LoginResponseDataDTO(
    val token: String
)
