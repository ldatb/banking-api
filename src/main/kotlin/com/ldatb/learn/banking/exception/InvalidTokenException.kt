package com.ldatb.learn.banking.exception

import java.lang.Exception

class InvalidTokenException(
    message: String? = "No message available",
    details: String? = "No details available",
    exception: String? = InvalidTokenException::class.simpleName
) : ApiException(
    message = message,
    details = details,
    exception = exception
)