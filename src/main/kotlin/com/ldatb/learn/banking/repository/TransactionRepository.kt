package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Implements the repository functions for the [com.ldatb.learn.banking.model.Transaction] entity.
 * Extends a [org.springframework.data.jpa.repository.JpaRepository] interface
 */
interface TransactionRepository : JpaRepository<Transaction, Long>
