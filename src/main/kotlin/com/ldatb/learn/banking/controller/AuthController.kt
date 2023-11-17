package com.ldatb.learn.banking.controller

import com.auth0.jwt.exceptions.JWTCreationException
import com.ldatb.learn.banking.dto.request.AuthenticationRequestDTO
import com.ldatb.learn.banking.dto.response.LoginResponseDTO
import com.ldatb.learn.banking.dto.response.LoginResponseDataDTO
import com.ldatb.learn.banking.exception.AccountNotFoundException
import com.ldatb.learn.banking.exception.ApiException
import com.ldatb.learn.banking.exception.InvalidTokenException
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.service.AccountService
import com.ldatb.learn.banking.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val accountService: AccountService
) {
    @PostMapping()
    fun loginToAccount(@RequestBody data: AuthenticationRequestDTO): ResponseEntity<Any> {
        if (accountService.getAccountByLogin(data.login) == null) {
            return ResponseEntity.badRequest().body(
                AccountNotFoundException(
                    message = "Account with login $data.login not found",
                    details = "Can't create token for unknown user ${data.login}"
                )
            )
        }

        val usernamePasswordToken = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(usernamePasswordToken)

        return try {
            val token = tokenService.generateToken(auth.principal as Account)
            ResponseEntity.ok(LoginResponseDTO(data = LoginResponseDataDTO(token)))
        } catch(exception: JWTCreationException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiException(
                    message = "The server was unable to create a token",
                    details = exception.message
                )
            )
        }
    }
}