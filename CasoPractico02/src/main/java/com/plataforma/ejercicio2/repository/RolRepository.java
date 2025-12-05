/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.repository;

/**
 *
 * @author chino
 */

import com.plataforma.ejercicio2.domain.Rol;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Long> {

    // Método necesario para que DataInitializer pueda encontrar el rol "ADMIN"
    Optional<Rol> findByNombre(String nombre); 
    
}


