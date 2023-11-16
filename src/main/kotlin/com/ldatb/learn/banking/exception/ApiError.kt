package com.ldatb.learn.banking.exception

import org.springframework.web.bind.annotation.ResponseBody

@JvmRecord
@ResponseBody
data class ApiError(
    val error: Boolean = true,
    val errors: List<String>,
)
