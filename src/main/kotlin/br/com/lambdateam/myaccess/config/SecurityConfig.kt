package br.com.lambdateam.myaccess.config

import br.com.lambdateam.myaccess.repository.UserRepository
import br.com.lambdateam.myaccess.security.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
class SecurityConfig(private val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {

    private val PUBLIC_MATCHERS = arrayOf<String>()

    public val PUBLIC_POST_MATCHERS = arrayOf(
        "/users"
    )

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            .antMatchers(HttpMethod.PATCH, *PUBLIC_POST_MATCHERS).permitAll()
            .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(authenticationManager(), userRepository))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}