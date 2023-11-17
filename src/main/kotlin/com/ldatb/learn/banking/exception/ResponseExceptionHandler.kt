package com.ldatb.learn.banking.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

/**
 * Custom handlers for the REST exceptions.
 * Extends a [ResponseEntityExceptionHandler] class
 */
@ControllerAdvice
@RestController
class ResponseExceptionHandler : ResponseEntityExceptionHandler() {
    /**
     * A generic exception handler. This is here so the server always return
     * something when it encounters an error
     */
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ApiException> =
        ResponseEntity<ApiException>(
            ApiException(message = ex.message),
            HttpStatus.INTERNAL_SERVER_ERROR
        )

    /**
     * Handles when a request body is not found or is missing required parameters
     */
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return handleExceptionInternal(
            ex,
            ApiException(
                message = "Invalid request body",
                details = ex.localizedMessage,
                exception = HttpMessageNotReadableException::class.simpleName
            ),
            headers,
            HttpStatus.BAD_REQUEST,
            request
        )
    }
}
