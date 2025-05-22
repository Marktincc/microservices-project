package com.example.servicesales.controllers;

import com.example.servicesales.entities.Sales;
import com.example.servicesales.services.ISalesService;

import com.example.servicesales.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class SalesController {

    @Autowired
    private ISalesService service;
    @Autowired
    private SalesService salesService;

    @GetMapping("/getAll")
    public List<Sales> getAll() {
        return service.getAll();
    }
    @GetMapping("/getAllPrueba")
    public List<Map<String, Object>> getAllPrueba() {
        return salesService.getAllPrueba();
    }
    @GetMapping("/getById/{id}")
    public Sales getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Sales> create(@RequestBody Sales sale) {
        Sales saleCreated = service.create(sale);
        return ResponseEntity.status(201).body(saleCreated);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Sales> update(@PathVariable Long id, @RequestBody Map<String, Object> dataUpdated) {
        Sales updatedSale = service.updateSale(id, dataUpdated);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Venta eliminada correctamente");
    }
}
