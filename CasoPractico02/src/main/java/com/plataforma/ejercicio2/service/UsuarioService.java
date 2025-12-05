/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.service;

/**
 *
 * @author chino
 */

import com.plataforma.ejercicio2.domain.Usuario;
import com.plataforma.ejercicio2.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() { return repo.findAll(); }

    public Usuario guardar(Usuario u) { return repo.save(u); }

    public Usuario obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}
