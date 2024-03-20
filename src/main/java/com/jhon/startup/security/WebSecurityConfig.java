package com.jhon.startup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1 = User.builder()
                .username("user2")
                .password("$2a$10$55Ns1Itbqf/kffZY9Ru3o.Gudseuhuwz.fm4wWoT7T0GgoR5hoqqK")
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin2")
                .password("$2a$10$55Ns1Itbqf/kffZY9Ru3o.Gudseuhuwz.fm4wWoT7T0GgoR5hoqqK")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    /**
     * Configura y devuelve un objeto SecurityFilterChain que define las reglas de seguridad para la aplicación.
     *
     * @param httpSecurity el objeto HttpSecurity utilizado para configurar las reglas de seguridad
     * @return un objeto SecurityFilterChain configurado con las reglas de seguridad especificadas
     * @throws Exception si hay algún error al configurar las reglas de seguridad
     */
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Configuración de las reglas de autorización
        httpSecurity.authorizeHttpRequests(
                        auth -> auth
                                // Permitir el acceso a "/personas" a todos los usuarios
                                .requestMatchers("/personas").permitAll()
                                // Requiere el rol "ADMIN" para acceder a "/personas/nueva"
                                .requestMatchers("/personas/nueva").hasAnyRole("ADMIN")
                                // Requiere el rol "ADMIN" para acceder a "/personas/editar/*" y "/personas/eliminar/*"
                                .requestMatchers("/personas/editar/*","/personas/eliminar/*").hasRole("ADMIN")
                                // Todas las demás solicitudes requieren autenticación
                                .anyRequest().authenticated())
                // Configuración del formulario de inicio de sesión
                .formLogin(form -> form.loginPage("/login").permitAll())
                // Configuración del proceso de cierre de sesión
                .logout(l -> l.permitAll())
                // Configuración para manejar excepciones de acceso denegado
                .exceptionHandling(e -> e.accessDeniedPage("/403"));

        // Devuelve el objeto SecurityFilterChain configurado
        return httpSecurity.build();
    }
}
