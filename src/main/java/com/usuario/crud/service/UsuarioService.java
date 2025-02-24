package com.usuario.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.crud.models.UsuarioModel;
import com.usuario.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioModel> listarUsuarios() {
        return (List<UsuarioModel>) repository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return repository.save(usuario);
    }
    public UsuarioModel obtenerUsuarioId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public UsuarioModel actualizarUsuarioId(UsuarioModel usuario) {
        return repository.save( usuario);
    }

    public void eliminarUsuario(Integer id) {
        repository.deleteById(id);
    }
}