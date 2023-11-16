package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun getTransactionByTransactionId(transactionId: Long): Transaction?
    fun getTransactionsBySenderTransferKey(transferKey: String): List<Transaction>?
    fun getTransactionsByRecipientTransferKey(transferKey: String): List<Transaction>?
}