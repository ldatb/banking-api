package com.ldatb.learn.banking.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Configures custom app properties and CORS
 */
@Configuration
@EnableConfigurationProperties(AppProperties::class)
class AppConfig {
    /**
     * A Bean that enables the custom app properties
     *
     * @return [AppProperties]
     */
    @Bean
    fun appConfiguration(): AppProperties = AppProperties()

    /**
     * A Bean that configures CORS for development purposes
     *
     * @return [CorsConfigurationSource]
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8089")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
