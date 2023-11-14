package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository)