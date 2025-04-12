package com.example.servicesales.repository;

import com.example.servicesales.entities.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository <Sales, Long>{
    List<Sales> findByidUsuarioAndIdProducto(Long idUsuario, Long productosId);

}
