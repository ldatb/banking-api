package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.AuthenticationRequestDTO
import com.ldatb.learn.banking.dto.response.LoginResponseDTO
import com.ldatb.learn.banking.dto.response.LoginResponseDataDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.security.TokenService
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
    private val tokenService: TokenService
) {
    @PostMapping()
    fun loginToAccount(@RequestBody data: AuthenticationRequestDTO): ResponseEntity<LoginResponseDTO> {
        val usernamePasswordToken = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(usernamePasswordToken)
        val token = tokenService.generateToken(auth.principal as Account)
        return ResponseEntity.ok(LoginResponseDTO(data = LoginResponseDataDTO(token)))
    }
}