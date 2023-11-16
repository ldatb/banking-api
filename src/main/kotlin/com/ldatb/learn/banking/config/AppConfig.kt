package com.ldatb.learn.banking.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AppProperties::class)
class AppConfig {
    @Bean
    fun appConfiguration(): AppProperties = AppProperties()
}