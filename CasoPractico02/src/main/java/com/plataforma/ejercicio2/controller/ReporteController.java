/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.controller;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private final UsuarioRepository repo;

    public ReporteController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/consultas")
    public String consultas(Model model) {
        model.addAttribute("usuarios", repo.findAll());
        return "reportes/consultas";
    }
}
