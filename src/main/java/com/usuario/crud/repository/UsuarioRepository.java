package com.usuario.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usuario.crud.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByEmail(String email);
}