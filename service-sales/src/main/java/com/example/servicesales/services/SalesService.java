package com.example.servicesales.services;

import com.example.servicesales.entities.Sales;
import com.example.servicesales.repository.ISalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private ISalesRepository repository;

    List<Sales> getAll() {
        return (List<Sales>) repository.findAll();
    }

}
