package com.ldatb.learn.banking.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(ex.message, ex.httpStatus.toString())
        return ResponseEntity(errorMessage, ex.httpStatus)
    }
}

class ErrorMessage(
    val message: String?,
    val status: String
)