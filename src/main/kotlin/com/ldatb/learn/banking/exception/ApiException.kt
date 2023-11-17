package com.ldatb.learn.banking.exception

import java.lang.Exception

open class ApiException(
    val error: Boolean = true,
    var message: String? = "No message available",
    var details: String? = "No details available",
    val exception: String? = Exception::class.simpleName
)
