package com.example.servicesales.repository;

import com.example.servicesales.entities.Sales;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISalesRepository extends CrudRepository <Sales, Long>{
    List<Sales> Id(Long id);

    List<Sales> findByCustomerId(Long customerId);

    List<Sales> findByProductosId(Long productosId);

    List<Sales> findByCustomerIdAndIdProducto(Long customerId, Long productosId);
}
