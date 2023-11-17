package com.ldatb.learn.banking.exception

class AccountAlreadyExistsException(
    message: String? = "No message available",
    details: String? = "No details available",
) : ApiException(
    message = message,
    details = details,
    exception = AccountAlreadyExistsException::class.simpleName
)