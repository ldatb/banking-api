package com.ldatb.learn.banking.util

import jakarta.servlet.http.HttpServletRequest

/**
 * Fetches a Bearer token from the [request]
 *
 * @param request is a instance of the [HttpServletRequest]
 * @return a String containing the token (without the `Bearer ` prefix)
 */
fun getTokenFromRequest(request: HttpServletRequest): String =
    request.getHeader("Authorization").replace("Bearer ", "")
