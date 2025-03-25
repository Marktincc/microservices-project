package com.example.servicessuppliers.services;

import com.example.servicessuppliers.entities.Supplier;

import java.util.List;
import java.util.Map;


public interface ISupplierService {
    List<Supplier> getAll();

    Supplier getById(Long Id);

    Supplier save(Supplier supplier);

    Supplier updatePartial(long id, Map<String, Object> updates);

    void delete (long id);
}
