package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.model.Transaction
import com.ldatb.learn.banking.repository.TransactionRepository
import org.springframework.stereotype.Service

/**
 * Implements CRUD methods for the [Transaction] entity by leveraging the [transactionRepository] repository
 *
 * @param transactionRepository is a [TransactionRepository] instance, mapped to the [Transaction] entity.
 */
@Service
class TransactionService(private val transactionRepository: TransactionRepository)
