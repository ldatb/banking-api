package com.ldatb.learn.banking.exception

import java.lang.Exception

/**
 * Is the base REST exception
 *
 * @param error is always true since this is an exception
 * @param message the message of the error
 * @param details the details of the error
 * @param exception is a String with the name of the exception
 */
open class ApiException(
    val error: Boolean = true,
    var message: String? = "No message available",
    var details: String? = "No details available",
    val exception: String? = Exception::class.simpleName
)
