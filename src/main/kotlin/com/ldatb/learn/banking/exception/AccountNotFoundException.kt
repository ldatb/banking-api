package com.ldatb.learn.banking.exception

/**
 * Is a REST exception for when an Account is not found.
 * Extends the [ApiException] class.
 *
 * @param message the message of the error
 * @param details the details of the error
 */
class AccountNotFoundException(
    message: String? = "No message available",
    details: String? = "No details available"
) : ApiException(
    message = message,
    details = details,
    exception = AccountNotFoundException::class.simpleName
)
