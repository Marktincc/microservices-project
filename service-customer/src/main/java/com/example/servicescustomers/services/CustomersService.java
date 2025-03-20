package com.example.servicescustomers.services;

import com.example.servicescustomers.entities.Rol;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.servicescustomers.entities.Customers;
import com.example.servicescustomers.repository.CustomersRepository;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CustomersService implements ICustomersService {

    @Autowired
    private CustomersRepository repository;

    public List<Customers> getAll() {
        return (List<Customers>) repository.findAll();
    }


    public Customers getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public Customers create(Customers Customers) {
        convertStringsToUpper(Customers);
        return repository.save(Customers);
    }

    public Customers updateCustomers(long id, @NotNull Map<String, Object> dataUpdated) {
        Customers customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        dataUpdated.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customers.class, key);
            if (field != null) {
                field.setAccessible(true);

                // Special handling for rol field
                if (key.equals("rol") && value instanceof String) {
                    try {
                        // Convert string to enum
                        Rol rolEnum = Rol.valueOf((String) value);
                        ReflectionUtils.setField(field, customer, rolEnum);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Invalid role value: " + value);
                    }
                } else {
                    ReflectionUtils.setField(field, customer, value);
                }
            }
        });

        return repository.save(customer);
    }
    @Override
    public void delete(long id) {

    }


    @Override
    public Map<String, String> login(String correo, String password) {
        return repository.findByCorreoAndPassword(correo, password)
                .map(customer -> {
                    if (Boolean.FALSE.equals(customer.getEstado())) {
                        throw new RuntimeException("Cuenta inactiva. Contacte al administrador.");
                    }
                    return Map.of("rol", customer.getRol().toString()); // Devuelve solo el rol
                })
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

    // Método para convertir los Strings en mayúsculas antes de guardar
    private void convertStringsToUpper(Customers customer) {
        if (customer.getNombre() != null) {
            customer.setNombre(customer.getNombre().toUpperCase());
        }
        if (customer.getApellidos() != null) {
            customer.setApellidos(customer.getApellidos().toUpperCase());
        }
        if (customer.getDireccion() != null) {
            customer.setDireccion(customer.getDireccion().toUpperCase());
        }
    }
}
