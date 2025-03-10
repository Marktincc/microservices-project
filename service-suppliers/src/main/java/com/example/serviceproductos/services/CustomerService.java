package com.example.serviceproductos.services;


import com.example.serviceproductos.entities.Customer;
import com.example.serviceproductos.repository.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements IcustomerService {

    @Autowired
    private CustomerRepository repository;


    public List<Customer> getAll() {
        return (List<Customer>) repository.findAll();

    }

    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }



    public Customer updatePartial(long id, @NotNull Map<String, Object> updates) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customer.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, customer, value);
            }
        });
        return repository.save(customer);
    }
}
