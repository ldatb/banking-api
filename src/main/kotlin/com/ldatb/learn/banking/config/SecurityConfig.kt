package com.ldatb.learn.banking.config

import com.ldatb.learn.banking.security.SecurityFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Configures the entire security filters and authentication for the application.
 * The purpose of this class is to solely enable JWT for auth.
 *
 * @param securityFilter is a [SecurityFilter] instance
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val securityFilter: SecurityFilter
) {
    /**
     * A Bean that configures the whole security filter chain for the application, this means
     * defining what routes require and don't require authentication, and how this
     * authentication is made
     *
     * @param httpSecurity is a spring annotation for the [HttpSecurity] method
     * @return a [SecurityFilterChain] instance
     */
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers(HttpMethod.POST, "/auth/**", "/account/register/**").permitAll()
                authorize.anyRequest().authenticated()
            }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    /**
     * A Bean the provides the authentication manager
     *
     * @param authenticationConfiguration is an instance of [AuthenticationConfiguration]
     * @return [AuthenticationManager]
     */
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager
            ?: throw Exception()

    /**
     * A simple bean to encode passwords
     *
     * @return [PasswordEncoder]
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
