package com.jhon.startup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * DataSource que proporciona la conexión con la base de datos.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * Configura la autenticación de Spring Security.
     *
     * @param builder El constructor de autenticación proporcionado por Spring Security.
     * @throws Exception Si ocurre algún error durante la configuración.
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        // Configura la autenticación basada en JDBC
        builder.jdbcAuthentication()
                // Utiliza el algoritmo de hash BCrypt para cifrar las contraseñas
                .passwordEncoder(new BCryptPasswordEncoder())
                // Especifica el DataSource que se utilizará para acceder a la base de datos
                .dataSource(dataSource)
                // Consulta SQL para obtener los detalles del usuario (nombre de usuario, contraseña y estado de habilitación)
                .usersByUsernameQuery("SELECT USERNAME,PASSWORD,ENABLED from tbl_users where username=?")
                // Consulta SQL para obtener los roles asociados con un usuario específico
                .authoritiesByUsernameQuery("select username,role from tbl_users where username=?");
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
