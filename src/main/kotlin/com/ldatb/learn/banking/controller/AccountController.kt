package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.CreateAccountDTO
import com.ldatb.learn.banking.dto.response.CreateAccountResponseDTO
import com.ldatb.learn.banking.dto.response.ErrorResponseDTO
import com.ldatb.learn.banking.repository.AccountRepository
import com.ldatb.learn.banking.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
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
    fun registerAccount(@RequestBody @Validated data: CreateAccountDTO): ResponseEntity<Any> {
        if (accountRepository.findAccountByLogin(data.login) != null) {
            return ResponseEntity.badRequest().body(
                ErrorResponseDTO(message = "Unable to create account with login ${data.login}")
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
