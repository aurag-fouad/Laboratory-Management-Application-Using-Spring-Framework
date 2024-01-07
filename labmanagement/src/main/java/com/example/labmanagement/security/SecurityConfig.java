package com.example.labmanagement.security;

import com.example.labmanagement.model.User;
import com.example.labmanagement.repository.UserRepository;
import com.example.labmanagement.service.UserService;
import com.example.labmanagement.web.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserRepository userRepository;
    private final UserService userService;
    private UserDetailsService userDetailsService;
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final AuthenticationProvider authenticationProvider;

        http.
                csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/**", "/error", "/js/**", "/css/**", "/fonts/**", "/images/**")
                .permitAll()
                .requestMatchers("/admin/**").hasRole("admin")
                .requestMatchers("/director/**").hasRole("director")
                .requestMatchers("/teacher/**").hasRole("teacher")
                .requestMatchers("/doctorant/**").hasRole("doctorant")
                .anyRequest()
                .authenticated()
                .and()
                .authenticationProvider(authenticationProvider())
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);
        return http.build();
    }
}
