package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.CreateAccountDTO
import com.ldatb.learn.banking.dto.response.CreateAccountResponseDTO
import com.ldatb.learn.banking.exception.ApiError
import com.ldatb.learn.banking.repository.AccountRepository
import com.ldatb.learn.banking.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    private val accountRepository: AccountRepository,
    private val accountService: AccountService
) {
    @PostMapping()
    @Transactional
    fun registerAccount(@RequestBody data: CreateAccountDTO, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(
                ApiError(errors = listOf( errors.toString()))
            )
        }

        if (accountRepository.findAccountByLogin(data.login) != null) {
            return ResponseEntity.badRequest().body(
                ApiError(errors = listOf("Unable to create account with login ${data.login}"))
            )
        }
        val newAccount = accountService.createAccount(data)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            CreateAccountResponseDTO(
                data = newAccount.toCreateAccountDetailsDTO()
            )
        )
    }
}
