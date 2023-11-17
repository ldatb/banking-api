package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val accountRepository: AccountRepository
) : UserDetailsService {
    // GET
    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByLogin(username)
}
