package com.example.servicescustomers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.servicescustomers.entities.Customers;
import com.example.servicescustomers.repository.CustomersRepository;

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

    @Override
    public Customers getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public Customers create(Customers category) {
        return null;
    }

    @Override
    public Customers updateCategory(long id, Map<String, Object> dataUpdated) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    
    @Override
    public Map<String, String> login(String correo, String password) {
        return repository.findByCorreoAndPassword(correo, password)
                .map(customer -> {
                    if (customer.getRol() == null) {
                        throw new RuntimeException("Usuario sin rol asignado");
                    }
                    Map<String, String> response = new HashMap<>();
                    response.put("rol", String.valueOf(customer.getRol()));
                    return response;
                })
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }
}
