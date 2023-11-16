package com.ldatb.learn.banking.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app")
data class AppProperties(
    var jwtToken: String = "my-32-character-ultra-secure-and-ultra-long-secret"
)