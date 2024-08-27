package org.elibrary.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/transaction/issue").hasAuthority("STUDENT") // Ensure /home is explicitly permitted
                        .requestMatchers("/transaction/return").hasAuthority("ADMIN")
                        .anyRequest().permitAll()) // Only authenticated users can access other endpoints
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(csrf->csrf.disable());// Enables HTTP Basic authentication

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder getEncoder(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println("user is: " + bCryptPasswordEncoder
//                .encode("user"));
        System.out.println("hiiiiiiiiiiiiiii");
//        System.out.println("password is: " + bCryptPasswordEncoder
//                .encode("password"));
        //return bCryptPasswordEncoder;
        return NoOpPasswordEncoder.getInstance();
    }
}
