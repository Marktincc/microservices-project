package com.example.serviceProveedores.services;


import com.example.serviceProveedores.entities.Supplier;
import com.example.serviceProveedores.repository.SupplierRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private SupplierRepository repository;

    public List<Supplier> getAll() {
        return (List<Supplier>) repository.findAll();
    }

    public Supplier getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }

    public Supplier updatePartial(long id, @NotNull Map<String, Object> updates) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Supplier.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, supplier, value);
            }
        });
        return repository.save(supplier);
    }

    public void delete (long id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        repository.delete(supplier);
    }
}
