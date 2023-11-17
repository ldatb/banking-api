package com.ldatb.learn.banking.exception

class AccountNotFoundException(
    message: String? = "No message available",
    details: String? = "No details available"
) : ApiException(
    message = message,
    details = details,
    exception = AccountNotFoundException::class.simpleName
)
