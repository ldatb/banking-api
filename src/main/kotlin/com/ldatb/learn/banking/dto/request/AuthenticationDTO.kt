package com.ldatb.learn.banking.dto.request

import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated


@JvmRecord
@Validated
final data class AuthenticationDTO(
    @field:NotNull("HERE")
    val login: String,

    @NotNull
    val password: String
)