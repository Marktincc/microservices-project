package servicecustomers.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicecustomers.entities.Customers;

import servicecustomers.services.ICustomersService;

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


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customers customers) {
        try {
            // Llamamos al servicio para obtener el rol
            Map<String, String> response = service.login(customers.getCorreo(), customers.getPassword());
            return ResponseEntity.ok(response); // Retorna el mapa como respuesta JSON
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new HashMap<String, String>() {{
                        put("message", "Credenciales inválidas");
                        put("status", "error");
                    }});
        }
    }


}
