package com.ldatb.learn.banking.repository

import com.ldatb.learn.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    // Used internally by the authentication service
    fun findByLogin(login: String): UserDetails
}