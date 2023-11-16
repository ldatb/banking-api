package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.exception.AccountNotFoundWithLoginException
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val accountRepository: AccountRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByLogin(login = username)
}