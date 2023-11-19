package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.CreateAccountRequestDTO
import com.ldatb.learn.banking.dto.response.AccountResponseDTO
import com.ldatb.learn.banking.dto.response.NotSelfAccountResponseDTO
import com.ldatb.learn.banking.exception.AccountAlreadyExistsException
import com.ldatb.learn.banking.exception.AccountNotFoundException
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.security.TokenService
import com.ldatb.learn.banking.service.AccountService
import com.ldatb.learn.banking.util.getTokenFromRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * The REST controller for the `/account` endpoint
 *
 * @param accountService is the service for the [Account] entity
 */
@RestController
@RequestMapping("account")
class AccountController(
    private val accountService: AccountService,
    private val tokenService: TokenService
) {
    /**
     * Registers a new Account in the database.
     * This route does not require authentication
     * @param data is the information required to create the account. Is an instance of [CreateAccountRequestDTO]
     * @return a [ResponseEntity]
     * @see [CreateAccountRequestDTO]
     */
    @PostMapping()
    @Transactional
    fun registerAccount(@RequestBody data: CreateAccountRequestDTO): ResponseEntity<Any> {
        // Verify that no account with the given login already exists
        if (accountService.getAccountByLogin(data.login) != null) {
            return ResponseEntity.badRequest().body(
                AccountAlreadyExistsException(
                    message = "Unable to create account with login ${data.login}",
                    details = "${data.login} is not a valid login"
                )
            )
        }

        // Call the account service to create the account
        val newAccount = accountService.createAccount(data)
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount.toAccountResponseDTO())
    }

    /**
     * Gets an user based on the request. If no query was given, returns a [AccountResponseDTO] instance,
     * but, if a query with an [Account.transferKey] is given, returns a [NotSelfAccountResponseDTO] instance
     * This is an authenticated route.
     *
     * @param request is an instance of [HttpServletRequest]
     * @return a [ResponseEntity] with either [AccountResponseDTO] data or [NotSelfAccountResponseDTO]
     */
    @GetMapping()
    fun getAccount(
        request: HttpServletRequest,
        @RequestParam(name = "key")
        transferKey: String?
    ): ResponseEntity<Any> {
        // Information was requested for another account
        if (transferKey != null) {
            val anotherAccount = accountService.getAccountFromTransferKey(transferKey)
                ?: return ResponseEntity.badRequest().body(
                    AccountNotFoundException(
                        message = "Account with transfer key $transferKey not found",
                        details = "Transfer key $transferKey doesn't belong to any account"
                    )
                )
            return ResponseEntity.ok(anotherAccount.toNotSelfAccountResponseDTO())
        }

        // Information was requested for self account
        val login = tokenService.validateToken(getTokenFromRequest(request))
        val selfAccount = accountService.getAccountByLogin(login)
            ?: return ResponseEntity.badRequest().body(
                AccountNotFoundException(
                    message = "Account not found",
                    details = "Bearer token corresponds to an unknown account"
                )
            )

        return ResponseEntity.ok(selfAccount.toAccountResponseDTO())
    }
}
