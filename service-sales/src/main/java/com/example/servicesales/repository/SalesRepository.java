package com.example.servicesales.repository;

import com.example.servicesales.entities.Sales;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesRepository extends CrudRepository <Sales, Long>{
    List<Sales> findByCustomerIdAndIdProducto(Long customerId, Long productosId);

}
