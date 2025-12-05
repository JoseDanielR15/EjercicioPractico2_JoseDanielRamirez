/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.ejercicio2.controller;

/**
 *
 * @author chino
 */
import com.plataforma.ejercicio2.domain.Rol;
import com.plataforma.ejercicio2.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rol")
public class RolController {

    private final RolService service;

    public RolController(RolService s) {
        this.service = s;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        // CORRECCIÓN: listar() -> getRoles()
        model.addAttribute("roles", service.getRoles()); 
        return "rol/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/form";
    }

    @PostMapping("/guardar")
    public String guardar(Rol r) {
        // CORRECCIÓN: guardar() -> save()
        service.save(r); 
        return "redirect:/rol/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // CORRECCIÓN: obtener(id) -> getRol(id) y manejo del Optional
        // Si el rol no se encuentra, devuelve un nuevo Rol (o podrías manejar un error 404)
        model.addAttribute("rol", service.getRol(id).orElse(new Rol())); 
        return "rol/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        // CORRECCIÓN: eliminar(id) -> delete(id)
        service.delete(id); 
        return "redirect:/rol/listado";
    }
}