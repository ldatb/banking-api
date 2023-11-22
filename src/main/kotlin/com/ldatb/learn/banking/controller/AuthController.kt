package com.ldatb.learn.banking.controller

import com.auth0.jwt.exceptions.JWTCreationException
import com.ldatb.learn.banking.domain.request.AuthenticationRequestDomain
import com.ldatb.learn.banking.domain.response.LoginResponseDTO
import com.ldatb.learn.banking.domain.response.LoginResponseDataDTO
import com.ldatb.learn.banking.exception.AccountNotFoundException
import com.ldatb.learn.banking.exception.ApiException
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * The REST controller for the `/auth` endpoint
 *
 * @param authenticationManager is an instance of the [AuthenticationManager] interface
 * @param tokenService is the service for the authentication processes
 * @param accountService is the service for the [Account] entity
 */
@RestController
@RequestMapping("auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val accountService: AccountService
) {
    /**
     * Login to account. Creates a JWT that can be used to authenticate the user into
     * other endpoints
     *
     * @param data is the information required to create the token. Is an instance of [AuthenticationRequestDomain]
     * @return a [ResponseEntity]
     * @see [AuthenticationRequestDomain]
     */
    @PostMapping()
    fun loginToAccount(@RequestBody data: AuthenticationRequestDomain): ResponseEntity<Any> {
        // No Account was found with this login, return AccountNotFoundException
        if (accountService.getAccountByLogin(data.login) == null) {
            return ResponseEntity.badRequest().body(
                AccountNotFoundException(
                    message = "Account with login $data.login not found",
                    details = "Can't create token for unknown user ${data.login}"
                )
            )
        }

        // Creates the authentication for the account
        val usernamePasswordToken = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(usernamePasswordToken)

        // Tries to create the account. Since it's a process that can fail, enclose it in a
        // try-catch and return either the token, or the error
        return try {
            val token = tokenService.generateToken(auth.principal as Account)
            ResponseEntity.status(HttpStatus.CREATED).body(
                LoginResponseDTO(data = LoginResponseDataDTO(token))
            )
        } catch (exception: JWTCreationException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiException(
                    message = "The server was unable to create a token",
                    details = exception.message
                )
            )
        }
    }
}
