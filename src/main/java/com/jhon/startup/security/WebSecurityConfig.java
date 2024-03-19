package com.jhon.startup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration //Esta registra Beans
public class WebSecurityConfig {
    /**
     * Users in memory
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        //user usuario
        UserDetails user1 = User.builder()
                .username("user1")
                .password("{bcrypt}$2a$10$0jNhWebT.ILcW0X/xywvXetU6UGCuJh8MYJH0XgFEtAGFbgUnMfoq")
                .roles("USER")
                .build();
        //user administrador
        UserDetails user2 = User.builder()
                .username("admin1")
                .password("{bcrypt}$2a$10$0jNhWebT.ILcW0X/xywvXetU6UGCuJh8MYJH0XgFEtAGFbgUnMfoq")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/personas").permitAll()
                        .requestMatchers("personas/nueva").hasAnyRole("ADMIN")
                        .requestMatchers("personas/editar/*","personas/eliminar/*").hasRole("ADMIN")
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }



}
