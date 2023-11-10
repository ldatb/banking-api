package com.ldatb.learn.banking.exception

import org.jetbrains.annotations.NotNull

class AccountNotFoundException(@NotNull message: String) : RuntimeException(message)