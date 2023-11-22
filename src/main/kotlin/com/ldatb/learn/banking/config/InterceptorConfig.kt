package com.ldatb.learn.banking.config

import com.ldatb.learn.banking.interceptor.AuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Configures the entire interceptors (middleware) for the application.
 *
 * @param authInterceptor is an [AuthInterceptor] instance
 */
@Configuration
class InterceptorConfig(
    private val authInterceptor: AuthInterceptor
) : WebMvcConfigurer {
    /**
     * Configures the interceptors
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/**", "/account/register/**")
    }
}