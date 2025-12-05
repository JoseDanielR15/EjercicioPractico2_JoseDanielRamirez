/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Rol;
import com.plataforma.ejercicio2.domain.Usuario;
import com.plataforma.ejercicio2.repository.RolRepository;
import com.plataforma.ejercicio2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired; // <-- Importación necesaria
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    // Inyección usando @Autowired en campos (para evitar conflictos de constructor)
    @Autowired
    private RolRepository rolRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Eliminar el constructor DataInitializer(...)

    @Override
    public void run(String... args) throws Exception {
        // ... (El resto del método run es el mismo) ...
        // ...
        
        // 1. Crear el rol ADMIN si no existe
        Rol adminRole = rolRepo.findByNombre("ADMIN").orElseGet(() -> {
            Rol newRol = new Rol();
            newRol.setNombre("ADMIN");
            return rolRepo.save(newRol);
        });
        
        // 2. Crear un usuario ADMIN de prueba si no existe
        if (usuarioRepo.findByEmail("admin@plataforma.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Super");
            admin.setApellido("Admin");
            admin.setEmail("admin@plataforma.com");
            // Contraseña: 123 (codificada con BCrypt)
            admin.setPassword(passwordEncoder.encode("123")); 
            admin.setActivo(true);
            admin.setRol(adminRole);
            usuarioRepo.save(admin);
            System.out.println(">>> Usuario ADMIN de prueba creado: admin@plataforma.com / 123");
        }
    }
}