package com.ldatb.learn.banking.security

import com.google.gson.Gson
import com.ldatb.learn.banking.exception.InvalidTokenException
import com.ldatb.learn.banking.service.AccountService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val accountService: AccountService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.recoverToken(request)
        if (token != null) {
            val login: String
            try {
                login = tokenService.validateToken(token)
            } catch (exception: Exception) {
                response.setHeader("Content-Type", "application/json")
                response.characterEncoding = "UTF-8"
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.print(Gson().toJson(InvalidTokenException(
                    message = "Invalid token",
                    details = exception.message,
                    exception = exception::class.simpleName
                )))
                response.writer.flush()
                return
            }

            val account = accountService.getAccountByLogin(login)!!
            val authentication = UsernamePasswordAuthenticationToken(
                account, null, account.authorities
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    // Private methods
    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        return authHeader.replace("Bearer ", "")
    }
}