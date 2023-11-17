package com.ldatb.learn.banking.security

import com.google.gson.Gson
import com.ldatb.learn.banking.exception.InvalidTokenException
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.service.AccountService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * The security filter implements the logic behind the JWT authentication.
 * It only implements a single function that's responsible for this.
 * Extends the [OncePerRequestFilter] class
 *
 * @param tokenService is the service for the authentication processes
 * @param accountService is the service for the [Account] entity
 */
@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val accountService: AccountService
) : OncePerRequestFilter() {
    /**
     * The logic for the JWT authentication
     * @param request the request of the call
     * @param response a response to be passed for the next filter
     * @param filterChain the filter chain
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.recoverToken(request)
        if (token != null) {
            // Try to validate the token, this means checking if the secret key, issuer,
            // expiration date and subject of the token are valid.
            // If the token is not valid, it returns a InvalidTokenException
            val login: String
            try {
                login = tokenService.validateToken(token)
            } catch (exception: Exception) {
                // For some reason, the token is not valid, so return the error
                response.setHeader("Content-Type", "application/json")
                response.characterEncoding = "UTF-8"
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.print(
                    Gson().toJson(
                        InvalidTokenException(
                            message = "Invalid token",
                            details = exception.message,
                            exception = exception::class.simpleName
                        )
                    )
                )
                response.writer.flush()
                return // Return here as we don't want anything else to be done
            }

            // If the code reaches here, it means that the token is valid,
            // so now we have to get the [Account] instance related to it
            val account = accountService.getAccountByLogin(login)!!
            val authentication = UsernamePasswordAuthenticationToken(
                account,
                null,
                account.authorities
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        // Call the next filter
        filterChain.doFilter(request, response)
    }

    /**
     * Recover the token from the request
     *
     * @param request the [HttpServletRequest]
     * @return an optional String containing the token
     */
    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        return authHeader.replace("Bearer ", "")
    }
}
