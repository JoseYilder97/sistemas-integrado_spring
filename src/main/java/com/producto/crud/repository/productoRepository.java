package com.producto.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producto.crud.models.productoModel;

public interface productoRepository extends JpaRepository<productoModel, Integer> {
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByEmail(String email);
}