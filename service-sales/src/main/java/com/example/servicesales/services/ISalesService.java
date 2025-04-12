package com.example.servicesales.services;

import com.example.servicesales.entities.Sales;
import com.example.servicesales.entities.SalesDTO;

import java.util.List;
import java.util.Map;

public interface ISalesService {

    List<Sales> getAll();

    Sales create(Sales sale);


    List<SalesDTO>getByIdCustomersAndProducts(Long customerId, Long productosId);

    Sales updateSales(long id, Map<String, Object> dataUpdated);

    void delete(long id);
}
