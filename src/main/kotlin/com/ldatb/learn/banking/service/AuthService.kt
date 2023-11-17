package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.model.Account
import com.ldatb.learn.banking.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * Implements CRUD methods for the [Account] entity by leveraging the [AccountRepository] repository.
 * Different to the [AccountService], this class is responsible for the CRUD methods related to the
 * [UserDetails] entity, and not the [Account] one
 *
 * @param accountRepository is a [AccountRepository] instance, mapped to the [Account] entity.
 */
@Service
class AuthService(
    private val accountRepository: AccountRepository
) : UserDetailsService {
    // GET

    /**
     * Fetches the [UserDetails] of an [Account] based on the [Account.login] parameter
     *
     * @param username is a String corresponding to [Account.login]
     * @return an [UserDetails] entity
     */
    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByLogin(username)
}
