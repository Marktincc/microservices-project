package com.example.servicesales.services;

import com.example.servicesales.entities.Sales;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public interface ISalesService {
    List<Sales> getAll();
    List<Map<String, Object>> getAllPrueba();

    Sales getById (Long id);
    Sales create (Sales sales);

    Sales updateSale (long id, Map<String, Object> dataUpdated);


    void delete (long id);

}
