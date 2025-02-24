package com.producto.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producto.crud.models.productoModel;
import com.producto.crud.repository.productoRepository;

@Service
public class productoService {
    @Autowired
    private productoRepository repository;

    public List<productoModel> listarproductos() {
        return (List<productoModel>) repository.findAll();
    }

    public productoModel guardarproducto(productoModel producto) {
        return repository.save(producto);
    }
    public productoModel obtenerproductoId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public productoModel actualizarproductoId(productoModel producto) {
        return repository.save( producto);
    }

    public void eliminarproducto(Integer id) {
        repository.deleteById(id);
    }
}