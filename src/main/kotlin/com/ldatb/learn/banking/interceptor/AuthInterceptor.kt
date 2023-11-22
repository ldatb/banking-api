package com.ldatb.learn.banking.interceptor

import com.google.gson.Gson
import com.ldatb.learn.banking.exception.InvalidTokenException
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.service.AccountService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

/**
 * This interceptor verifies all protected routes to verify the JWT
 * token given, and returns the user if valid.
 *
 * @param tokenService is a [TokenService] instance
 */
@Component
class AuthInterceptor(
    private val tokenService: TokenService,
    private val accountService: AccountService
) : HandlerInterceptor {
    /**
     * Verifies the JWT token before the controller takes control of the request
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return try {
            // Do not use com.ldatb.learn.banking.util.getTokenFromRequest here as we want
            // to make sure that the token exists
            val authHeader = request.getHeader("Authorization")
            val token = authHeader.replace("Bearer ", "")
            val login = tokenService.validateToken(token)

            // Verify account exists
            val account = accountService.getAccountByLogin(login)
            if (account == null) {
                // Account not found, so return error
                response.setHeader("Content-Type", "application/json")
                response.characterEncoding = "UTF-8"
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.print(
                    Gson().toJson(
                        InvalidTokenException(
                            message = "Invalid token",
                            details = "Token does not belong to any account",
                            exception = InvalidTokenException::class.simpleName
                        )
                    )
                )
                response.writer.flush()
                return false
            }

            // All good, return true
            true
        } catch (ex: Exception) {
            response.setHeader("Content-Type", "application/json")
            response.characterEncoding = "UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.print(
                Gson().toJson(
                    InvalidTokenException(
                        message = "Invalid token",
                        details = ex.message,
                        exception = ex::class.simpleName
                    )
                )
            )
            response.writer.flush()
            return false
        }
    }
}