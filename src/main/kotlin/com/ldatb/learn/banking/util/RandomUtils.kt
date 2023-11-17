package com.ldatb.learn.banking.util

import java.security.SecureRandom

/**
 * Generates a random 6 digit number (between 100000 and 999999)
 *
 * @return an UInt value between 100000 and 999999 (inclusive)
 */
fun generateRandomSecretToken(): UInt = SecureRandom().nextInt(100000, 1000000).toUInt()
