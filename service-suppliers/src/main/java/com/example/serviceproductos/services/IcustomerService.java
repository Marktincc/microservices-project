package com.example.serviceproductos.services;

import com.example.serviceproductos.entities.Customer;

import java.util.List;
import java.util.Map;


public interface IcustomerService {
    List<Customer> getAll();

    Customer getById(Long Id);

    Customer save(Customer customer);


    Customer updatePartial(long id, Map<String, Object> updates);


}
