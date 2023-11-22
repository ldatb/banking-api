package com.ldatb.learn.banking.exception

/**
 * Is a REST exception for when a request is not valid, for any reason.
 * Extends the [ApiException] class.
 *
 * @param message the message of the error
 * @param details the details of the error
 */
class InvalidRequestException(
    message: String? = "The request is not valid",
    details: String? = "No details available",
) : ApiException(
    message = message,
    details = details,
    exception = InvalidRequestException::class.simpleName
)
