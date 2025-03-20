package com.example.servicescustomers.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.servicescustomers.entities.Customers;

import com.example.servicescustomers.services.ICustomersService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/usuarios")
public class CustomersController {
    @Autowired
    private ICustomersService service;

//comentario

    @GetMapping("/getAll")
    public List<Customers> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{id}")
    public Customers getById(@PathVariable Long id) {
        return service.getById(id);

    }
    @PostMapping("/create")
    public ResponseEntity<Customers> create (@RequestBody Customers Customers) {
        Customers CustomersCreated = service.create(Customers);
        return ResponseEntity.status(201).body(CustomersCreated);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Customers> update (@PathVariable Long id, @RequestBody Map<String, Object> dataUpdated) {
        Customers CustomersUpdated = service.updateCustomers(id, dataUpdated);
        return ResponseEntity.ok(CustomersUpdated);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customers customers) {
        try {
            // Intentamos obtener el rol del usuario
            Map<String, String> response = service.login(customers.getCorreo(), customers.getPassword());
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            String message = e.getMessage();


            if ("Cuenta inactiva. Contacte al administrador.".equals(message)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                        "message", message,
                        "status", "inactive"
                ));
            }


            if ("Credenciales inválidas".equals(message)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "message", message,
                        "status", "error"
                ));
            }


            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", "Error en el servidor",
                    "status", "error"
            ));
        }
    }


}
