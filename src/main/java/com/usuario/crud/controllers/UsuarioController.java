package com.usuario.crud.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;

import com.usuario.crud.models.UsuarioModel;
import com.usuario.crud.repository.UsuarioRepository;
import com.usuario.crud.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public String getAllUsuarios(Model model) {
        model.addAttribute("usuarios", service.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", service.obtenerUsuarioId(id));
        return "editar";

    }

    @GetMapping("/actualizar/{id}")
    public String ectualizarUsuario(@PathVariable Integer id, Model model) {
        UsuarioModel usuario = service.actualizarUsuarioId(service.obtenerUsuarioId(id));
        usuario.setId(id);
        usuario.setNombre(usuario.getNombre());
        usuario.setTipoDocumento(usuario.getTipoDocumento());
        usuario.setNumeroDocumento(usuario.getNumeroDocumento());
        usuario.setEmail(usuario.getEmail());
        usuario.setTelefono(usuario.getTelefono());
        service.actualizarUsuarioId(usuario);
        return "redirect:/usuarios";

    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute UsuarioModel usuario, RedirectAttributes redirectAttributes) {
        if (repository.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
            redirectAttributes.addFlashAttribute("error", "El número de documento ya está registrado.");
            return "redirect:/usuarios/formulario"; // Redirige al formulario con un mensaje de error
        }

        if (repository.existsByEmail(usuario.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "El email ya está registrado.");
            return "redirect:/usuarios/formulario";
        }

        repository.save(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario guardado exitosamente.");
        return "redirect:/usuarios";
    }

    @PostMapping("eliminar/{id}")
    public String deleteUsuario(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

}
