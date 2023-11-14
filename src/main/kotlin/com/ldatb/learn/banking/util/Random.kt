package com.ldatb.learn.banking.util

import java.security.SecureRandom

// Returns a random 6 digit number (between 100000 and 999999)
fun generateRandomSecretToken(): UInt = SecureRandom().nextInt(100000, 1000000).toUInt()