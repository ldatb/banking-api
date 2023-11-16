package com.ldatb.learn.banking.controller

import com.ldatb.learn.banking.dto.request.CreateAccountDTO
import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    private val accountRepository: AccountRepository
) {
    @PostMapping()
    fun registerAccount(@RequestBody @Validated data: CreateAccountDTO): ResponseEntity<Account> {
        TODO()
    }
}