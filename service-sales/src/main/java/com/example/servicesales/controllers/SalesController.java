package com.example.servicesales.controllers;


import com.example.servicesales.entities.Sales;
import com.example.servicesales.services.ISalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ventas")
public class SalesController {

    //LE FALTA definir la entidad de donde va ir a mirar si existe ese metodo
    @Autowired
    private ISalesService salesService;


    @GetMapping("/getVentas")
    public List<Sales> getAll(){
        return salesService.getAll();
    }


}
