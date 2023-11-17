package com.ldatb.learn.banking.model

/**
 * Possible account roles. These are used by the application's security layer
 * to verify what a user can / cannot do.
 * Check [Account] to see how it's used
 *
 * @see [Account]
 */
@Suppress("UNUSED_PARAMETER")
enum class AccountRoles(s: String) {
    USER("USER"),
    ADMIN("ADMIN")
}
