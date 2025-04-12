package com.example.servicessuppliers.controllers;

import com.example.servicessuppliers.entities.Supplier;
import com.example.servicessuppliers.services.ISupplierService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proveedores")
public class SupplierController {
    @Autowired
    private ISupplierService service;

    @GetMapping("/getAll")
    public List<Supplier> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{id}")
    public Supplier getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        Supplier newSupplier = service.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Supplier> updateById(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Supplier updatedSupplier = service.updatePartial(id, updates);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Proveedor eliminado correctamente");
    }

}
