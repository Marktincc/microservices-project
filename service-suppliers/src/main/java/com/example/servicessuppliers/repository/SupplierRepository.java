package com.example.servicessuppliers.repository;

import com.example.servicessuppliers.entities.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
