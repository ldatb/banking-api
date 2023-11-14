package com.ldatb.learn.banking.exception

import org.springframework.http.HttpStatus

open class CustomException(
    override val message: String,
    val httpStatus: HttpStatus
) : Exception(message)