package com.ldatb.learn.banking.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * This data class is responsible for specifying custom parameters of the `application.properties`
 * or `application.yaml` file.
 *
 * @param jwtToken is the secret key used to sign JWT tokens
 */
@Configuration
@ConfigurationProperties(prefix = "app")
data class AppProperties(
    var jwtToken: String = "my-32-character-ultra-secure-and-ultra-long-secret"
)
