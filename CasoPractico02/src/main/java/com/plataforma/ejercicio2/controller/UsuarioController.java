/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.controller;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Usuario;
import com.plataforma.ejercicio2.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired; // Importación necesaria
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioController {

    // Inyección de campos usando @Autowired para evitar problemas de orden de inicialización
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // NOTA: Se ELIMINA el constructor para que @Autowired resuelva las dependencias.

    /**
     * Obtiene una lista de todos los usuarios. (Antiguo método 'listar')
     * @return Lista de objetos Usuario.
     */
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        // Conversión segura de Iterable<Usuario> a List<Usuario>
        return StreamSupport
                .stream(usuarioRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Guarda o actualiza un usuario, codificando la contraseña si es necesario. (Antiguo método 'guardar')
     * @param usuario Objeto Usuario a guardar.
     */
    @Transactional
    public void save(Usuario usuario) {
        
        // Lógica de Codificación de Contraseña
        if (usuario.getId() == null) {
            // Es un usuario nuevo: la contraseña DEBE ser codificada.
            // Se asume que en el formulario de "nuevo" siempre se envía la contraseña.
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            
        } else if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            // Es una edición y la contraseña fue modificada (el campo no está vacío): codificar la nueva.
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            
        } else {
            // Es una edición y la contraseña está vacía: mantener la contraseña existente.
            // Recuperar el hash de la contraseña actual de la base de datos.
            usuarioRepo.findById(usuario.getId()).ifPresent(u -> 
                usuario.setPassword(u.getPassword())
            );
        }
        
        // Guardar el usuario (con la contraseña ya codificada o recuperada)
        usuarioRepo.save(usuario);
    }

    /**
     * Busca un usuario por su ID. (Antiguo método 'obtener')
     * @param id ID del usuario.
     * @return Optional que contiene el Usuario si existe.
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepo.findById(id);
    }

    /**
     * Elimina un usuario por su ID. (Antiguo método 'eliminar')
     * @param id ID del usuario a eliminar.
     */
    @Transactional
    public void delete(Long id) {
        usuarioRepo.deleteById(id);
    }
    
    // Aquí puedes añadir tus métodos de consulta avanzados, por ejemplo:
    
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }
}
