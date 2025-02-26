package com.example.servicesuppliers.controllers;

import com.example.servicesuppliers.entities.Customer;
import com.example.servicesuppliers.repository.CustomerRepository;
import com.example.servicesuppliers.services.IcustomerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proveedores")
public class CustomerController {
    @Autowired
    private IcustomerService service;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/getAll")
    public List<Customer> getAll() {return service.getAll();}

    @GetMapping("/getById/{id}")
    public Customer getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        Customer newCustomer = service.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @PatchMapping ("/update/{id}")
    public ResponseEntity getById(@PathVariable Long id,@RequestBody Map<String, Object> updates){
        Customer updateCustomer = service.updatePartial(id,updates);
        return ResponseEntity.ok(updateCustomer);
    }
}
