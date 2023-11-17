package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository)
