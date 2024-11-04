package com.spring.security;

import com.spring.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUnAuthResponse jwtUnAuthResponse;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        return http
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/api/v1/auth/login").permitAll()
                                        .requestMatchers("/api/v1/user/**").hasAuthority("ROLE_user") // Include ROLE_ prefix
                                        .requestMatchers("/api/v1/role/**").hasAuthority("ROLE_admin") // Include ROLE_ prefix
                                        .anyRequest().authenticated()

                )
                .userDetailsService(customUserDetailsService)
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtUnAuthResponse))
//                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(null))
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults()).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authManagerBuilder.build();
    }
    @Bean
    public AuthFilter authFilter () {
        return new AuthFilter();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//
//    }


}
