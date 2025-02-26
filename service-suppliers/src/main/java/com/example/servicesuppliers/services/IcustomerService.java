package com.example.servicesuppliers.services;

import com.example.servicesuppliers.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface IcustomerService {
    List<Customer> getAll();
    Customer getById(Long Id);

    Customer save(Customer customer);

    Customer updatePartial(Long id, Map<String, Object> updates);
}
