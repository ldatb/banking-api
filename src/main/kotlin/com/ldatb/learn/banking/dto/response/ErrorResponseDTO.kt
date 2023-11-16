package com.ldatb.learn.banking.dto.response

import org.springframework.web.bind.annotation.ResponseBody

@JvmRecord
@ResponseBody
data class ErrorResponseDTO(
    val error: Boolean = true,
    val message: String
)