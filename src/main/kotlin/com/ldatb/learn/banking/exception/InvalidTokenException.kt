package com.ldatb.learn.banking.exception

/**
 * Is a REST exception for when a token is not valid, for any reason.
 * Extends the [ApiException] class.
 *
 * @param message the message of the error
 * @param details the details of the error
 * @param exception is a String with the name of the exception
 */
class InvalidTokenException(
    message: String? = "No message available",
    details: String? = "No details available",
    exception: String? = InvalidTokenException::class.simpleName
) : ApiException(
    message = message,
    details = details,
    exception = exception
)
