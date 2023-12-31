package com.ldatb.learn.banking.security

import com.ldatb.learn.banking.config.AppConfig
import com.ldatb.learn.banking.model.Account
import org.junit.jupiter.api.Assertions.* // ktlint-disable no-wildcard-imports
import org.junit.jupiter.api.Test

/**
 * Provide tests for the [TokenService]
 * @see [TokenService]
 */
class TokenServiceTest {
    /**
     * Declare all constructors here as a test class can't have more than one
     * primary constructor
     */
    private val appConfig: AppConfig = AppConfig()
    private val tokenService: TokenService = TokenService(appConfig)
    private val mockkAccount: Account = Account(
        login = "mock-login",
        hashedPassword = "mock-password",
        firstName = "Mock",
        lastName = "Account"
    )

    /**
     * Verify that the [TokenService.generateToken]
     * is able to create a token based on a [Account]
     */
    @Test
    fun testGenerateToken() {
        // when
        val result = tokenService.generateToken(mockkAccount)

        // then
        assertNotNull(result)
    }

    /**
     * Verify that the token generated by [TokenService.generateToken]
     * is valid. This means that the [TokenService.validateToken] should
     * return an [Account.login] value
     */
    @Test
    fun testValidateToken() {
        // given
        val token = tokenService.generateToken(mockkAccount)

        // when
        val result = tokenService.validateToken(token)

        // then
        assertEquals(mockkAccount.login, result)
    }
}
