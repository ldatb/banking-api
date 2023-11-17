package com.ldatb.learn.banking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.ldatb.learn.banking.config.AppConfig
import com.ldatb.learn.banking.model.Account
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class TokenService(
    appConfig: AppConfig
) {
    private val secretKey = Algorithm.HMAC256(appConfig.appConfiguration().jwtToken)
    private val tokenIssuer = "ldatb.com"

    fun generateToken(account: Account): String {
        try {
            return JWT.create()
                .withIssuer(tokenIssuer)
                .withSubject(account.login)
                .withExpiresAt(generateExpirationDate())
                .sign(secretKey)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generating JWT token", exception)
        }
    }

    fun validateToken(token: String): String {
        return try {
            JWT.require(secretKey)
                .withIssuer(tokenIssuer)
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            ""
        }
    }

    // Private functions
    private fun generateExpirationDate(): Date =
        Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
}