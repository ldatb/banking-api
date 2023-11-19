package com.ldatb.learn.banking.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.service.AccountService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.* // ktlint-disable no-wildcard-imports

/**
 * Provide tests for the [AccountController]
 * @see [AccountController]
 */
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AccountController::class)
class AccountControllerTest {
    /**
     * Configure everything required to run tests on a controller
     */
    @TestConfiguration
    class AccountControllerConfig {
        @Bean
        fun accountService() = mockk<AccountService>()

        @Bean
        fun tokenService() = mockk<TokenService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var tokenService: TokenService

    /**
     * Declare mock data required by the tests
     */
    private val mockkAccount: Account = Account(
        login = "mock-login",
        hashedPassword = "mock-password",
        firstName = "Mock",
        lastName = "Account"
    )

    private val createAccountMock = CreateAccountRequestDTO(
        login = mockkAccount.login,
        password = mockkAccount.hashedPassword,
        firstName = mockkAccount.firstName,
        lastName = mockkAccount.lastName
    )

    /**
     * Verify that all services and context were loaded
     */
    @Test
    fun contextLoads() {
    }

    /**
     * Verify that the [AccountController.registerAccount]
     * is able to create an [Account] when provided with
     * the [CreateAccountRequestDTO]
     */
    @Test
    fun testRegisterAccount() {
        // given
        every { accountService.getAccountByLogin(createAccountMock.login) } returns null
        every { accountService.createAccount(createAccountMock) } returns mockkAccount

        // when
        mockMvc.perform(
            post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsBytes(createAccountMock))
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value(false))
            .andExpect(jsonPath("$.data.login").value(mockkAccount.login))

        // then
        verify { accountService.createAccount(createAccountMock) }
    }

    /**
     * Verify that the [AccountController.getAccount]
     * is able to get self user [Account] when provided with
     * the JWT token
     */
    @Test
    fun testGetAccountSelf() {
        // given
        every { tokenService.validateToken(any()) } returns mockkAccount.login
        every { accountService.getAccountByLogin(mockkAccount.login) } returns mockkAccount

        // when
        mockMvc.perform(
            get("/account")
                .header("Authorization", "any-token")
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value(false))
            .andExpect(jsonPath("$.data.login").value(mockkAccount.login))

        // then
        verify { accountService.getAccountByLogin(mockkAccount.login) }
    }

    /**
     * Verify that the [AccountController.getAccount]
     * is able to another user's [Account] when provided with
     * the transfer key
     */
    @Test
    fun testGetAccountNotSelf() {
        // given
        val mockkAccountOther = Account(
            login = "mock-login-another",
            hashedPassword = "mock-password",
            firstName = "Other",
            lastName = "Mock"
        )
        every { accountService.getAccountFromTransferKey(mockkAccountOther.transferKey) } returns mockkAccountOther

        // when
        mockMvc.perform(
            get("/account")
                .header("Authorization", "any-token")
                .param("key", mockkAccountOther.transferKey)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value(false))
            .andExpect(jsonPath("$.data.transferKey").value(mockkAccountOther.transferKey))

        // then
        verify { accountService.getAccountFromTransferKey(mockkAccountOther.transferKey) }
    }
}
