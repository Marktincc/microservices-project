package com.example.servicescustomers.services;


import com.example.servicescustomers.entities.Customers;

import java.util.List;
import java.util.Map;

public interface ICustomersService {

    List<Customers> getAll ();

    Customers getById (Long id);

    Customers create (Customers category);

    Customers updateCategory (long id, Map<String, Object> dataUpdated);

    void delete (long id);

    //LOGIN
    Map<String,String> login (String correo, String password);
}
