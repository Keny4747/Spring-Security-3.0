package com.example.springsecurityJB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
        /*UserDetails admin = User.withUsername("Keny")
                .password(encoder.encode("12345"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("John")
                .password(encoder.encode("12345"))
                .roles("USER")
                .build();
                 return new InMemoryUserDetailsManager(admin,user);
         */

       return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/hello").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").authenticated()
                .and().formLogin()
                .and()
                .build();
        */

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/hello","/api/new").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").authenticated()
                .and().formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider;
    }
}
