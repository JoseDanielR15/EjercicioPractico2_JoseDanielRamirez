/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Usuario;
import com.plataforma.ejercicio2.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repo;

    public CustomUserDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

        return User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRol().getNombre().toUpperCase())
                .build();
    }
}
