/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.service;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Rol;
import com.plataforma.ejercicio2.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importación para manejo de transacciones

/**
 * Servicio que implementa la lógica de negocio para la entidad Rol.
 */
@Service
public class RolService {

    private final RolRepository rolRepo;

    // Inyección de dependencias por constructor
    public RolService(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    /**
     * Obtiene una lista de todos los roles, convirtiendo el Iterable a List.
     * @return Lista de objetos Rol.
     */
    @Transactional(readOnly = true)
    public List<Rol> getRoles() {
        // CORRECCIÓN: Conversión segura de Iterable<Rol> a List<Rol>
        return StreamSupport
                .stream(rolRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Busca un rol por su ID.
     * @param rolId ID del rol.
     * @return Optional que contiene el Rol si existe.
     */
    @Transactional(readOnly = true)
    public Optional<Rol> getRol(Long rolId) {
        return rolRepo.findById(rolId);
    }

    /**
     * Guarda o actualiza un rol.
     * @param rol Objeto Rol a guardar.
     */
    @Transactional
    public void save(Rol rol) {
        rolRepo.save(rol);
    }

    /**
     * Elimina un rol por su ID.
     * @param rolId ID del rol a eliminar.
     */
    @Transactional
    public void delete(Long rolId) {
        rolRepo.deleteById(rolId);
    }
}

