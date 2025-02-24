package com.producto.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.producto.crud.models.productoModel;
import com.producto.crud.repository.productoRepository;
import com.producto.crud.service.productoService;

@Controller
@RequestMapping("/productos")
public class productoController {

    @Autowired
    private productoService service;

    @Autowired
    private productoRepository repository;

    @GetMapping
    public String getAllproductos(Model model) {
        model.addAttribute("productos", service.listarproductos());
        return "productos"; // Asegúrate de que esta vista exista
    }

    @GetMapping("/editar/{id}")
    public String editarproducto(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", service.obtenerproductoId(id));
        return "formulario";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new productoModel());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarproducto(@ModelAttribute productoModel producto, RedirectAttributes redirectAttributes) {
        if (repository.existsByNumeroDocumento(producto.getNumeroDocumento())) {
            redirectAttributes.addFlashAttribute("error", "El número de documento ya está registrado.");
            return "redirect:/productos/nuevo"; // Redirige al formulario con un mensaje de error
        }

        if (repository.existsByEmail(producto.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "El email ya está registrado.");
            return "redirect:/productos/nuevo"; // Redirige al formulario con un mensaje de error
        }

        repository.save(producto);
        redirectAttributes.addFlashAttribute("success", "producto guardado exitosamente.");
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String deleteproducto(@PathVariable Integer id) {      
        service.eliminarproducto(id);
        return "redirect:/productos";
    }
}
