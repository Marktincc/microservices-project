package com.example.servicesales.repository;

import com.example.servicesales.entities.Sales;
import org.springframework.data.repository.CrudRepository;

public interface ISalesRepository extends CrudRepository <Sales, Long>{
}
