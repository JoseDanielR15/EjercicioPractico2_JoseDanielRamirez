/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.repository;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByRol_Nombre(String nombre);

    List<Usuario> findByFechaCreacionBetween(Timestamp desde, Timestamp hasta);

    List<Usuario> findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCase(
            String email, String nombre
    );
}