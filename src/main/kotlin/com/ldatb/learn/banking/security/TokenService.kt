package com.ldatb.learn.banking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.ldatb.learn.banking.config.AppConfig
import com.ldatb.learn.banking.model.Account
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * The token service is responsible for generating and validating JWT
 *
 * @param appConfig contains the value for the secret key
 * @property secretKey is the key used to sign the tokens. Encrypted with HMAC256
 * @property tokenIssuer the issuer of the token
 */
@Service
class TokenService(
    appConfig: AppConfig
) {
    private val secretKey = Algorithm.HMAC256(appConfig.appConfiguration().jwtToken)
    private val tokenIssuer = "ldatb.com"

    /**
     * Generates a JWT for a [Account] instance
     *
     * @param account is the [Account] to be used
     * @return a String containing the token
     */
    fun generateToken(account: Account): String =
        JWT.create()
            .withIssuer(tokenIssuer)
            .withSubject(account.login)
            .withExpiresAt(generateExpirationDate())
            .sign(secretKey)

    /**
     * Validated a JWT for a [Account] instance
     *
     * @param token is a String containing the token
     * @return the [Account.login] value
     */
    fun validateToken(token: String): String =
        JWT.require(secretKey)
            .withIssuer(tokenIssuer)
            .build()
            .verify(token)
            .subject

    /**
     * Generated an expiration date of `now + 1 hour` to the token
     * @return a [Date] of `now + 1 hour`
     */
    private fun generateExpirationDate(): Date =
        Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.MIN))
}
