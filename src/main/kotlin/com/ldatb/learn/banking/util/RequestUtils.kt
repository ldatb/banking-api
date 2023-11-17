package com.ldatb.learn.banking.util

import jakarta.servlet.http.HttpServletRequest

open class RequestUtils {
    open fun getTokenFromRequest(request: HttpServletRequest): String =
        request.getHeader("Authorization").replace("Bearer ", "")
}
