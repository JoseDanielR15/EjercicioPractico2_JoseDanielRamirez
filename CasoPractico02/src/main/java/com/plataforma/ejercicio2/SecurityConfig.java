/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2;

/**
 *
 * @author chino
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService; // Asegúrate de tener esta importación

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // Inyección de la clase CustomUserDetailsService (que implementa UserDetailsService)
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 1. Bean para el codificador de contraseñas (BCryptPasswordEncoder)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Bean DaoAuthenticationProvider CORREGIDO
    // Se pasa el UserDetailsService al constructor, como lo requiere tu versión de Spring Security.
    @Bean
    public DaoAuthenticationProvider authProvider(BCryptPasswordEncoder passwordEncoder) {
        // CORRECCIÓN: Se pasa userDetailsService al constructor.
        DaoAuthenticationProvider p = new DaoAuthenticationProvider(userDetailsService); 
        
        // Se usa el Bean BCryptPasswordEncoder inyectado
        p.setPasswordEncoder(passwordEncoder);
        return p;
    }

    // 3. Configuración de la cadena de filtros de seguridad y las restricciones de URL
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas: login, css, js
                .requestMatchers("/login", "/css/**", "/js/**").permitAll() 
                
                // RESTRICCIÓN: /rol/** requiere ADMIN
                .requestMatchers("/rol/**").hasRole("ADMIN") 
                
                // RESTRICCIÓN: CRUD de usuarios (guardar/editar/eliminar) requiere ADMIN
                .requestMatchers("/usuario/nuevo", "/usuario/guardar", "/usuario/editar/**", "/usuario/eliminar/**").hasRole("ADMIN")

                // RESTRICCIÓN: Listado de usuarios y reportes requiere ADMIN o PROFESOR
                .requestMatchers("/usuario/listado", "/reportes/**").hasAnyRole("ADMIN", "PROFESOR") 
                
                // Cualquier otra petición requiere autenticación (incluye la ruta "/")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/acceso-denegado")
            )
            .logout(logout -> logout.permitAll());
        
        return http.build();
    }
}