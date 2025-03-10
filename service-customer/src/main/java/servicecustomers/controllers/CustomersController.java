package servicecustomers.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicecustomers.entities.Customers;
import servicecustomers.repository.CustomersRepository;
import servicecustomers.services.ICustomersService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class CustomersController {
    @Autowired
    private ICustomersService service;

    @Autowired
    private CustomersRepository repository;

    @GetMapping("/getAll")
    public List<Customers> getAll() {return service.getAll();}


}
