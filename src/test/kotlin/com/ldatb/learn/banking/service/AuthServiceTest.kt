package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.* // ktlint-disable no-wildcard-imports
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.UserDetails

/**
 * Provide tests for the [AuthService]
 * @see [AuthService]
 */
class AuthServiceTest {
    /**
     * Declare all constructors here as a test class can't have more than one
     * primary constructor
     */
    private val accountRepository: AccountRepository = mockk<AccountRepository>()
    private val authService: AuthService = AuthService(accountRepository)
    private val mockkAccount: Account = Account(
        login = "mock-login",
        hashedPassword = "mock-password",
        firstName = "Mock",
        lastName = "Account"
    )

    /**
     * Verify that the [AuthService.loadUserByUsername]
     * is able to create an [UserDetails] based on a [Account.login],
     * which in this case, is called by the [Account.getUsername] function
     * @see [Account.getUsername]
     */
    @Test
    fun testLoadUserByUsername() {
        // given
        every { accountRepository.findByLogin(mockkAccount.login) } returns mockkAccount

        // when
        val result = authService.loadUserByUsername(mockkAccount.login)

        // then
        assertEquals(mockkAccount, result)
    }
}
