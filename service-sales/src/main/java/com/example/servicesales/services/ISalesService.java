package com.example.servicesales.services;

import com.example.servicesales.entities.Sales;
import com.example.servicesales.entities.SalesDTO;

import java.util.List;

public interface ISalesService {

    List<Sales> getAll();


    List<SalesDTO>getByIdCustomersAndProducts(Long customerId, Long productosId);
}
