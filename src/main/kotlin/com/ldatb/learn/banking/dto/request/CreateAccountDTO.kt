package com.ldatb.learn.banking.dto.request

import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated

@JvmRecord
@Validated
final data class CreateAccountDTO(
    @NotNull
    val login: String,

    @NotNull
    val password: String,

    @NotNull
    val firstName: String,

    @NotNull
    val lastName: String,
)