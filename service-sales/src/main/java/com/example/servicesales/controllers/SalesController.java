package com.example.servicesales.controllers;


import com.example.servicesales.entities.Sales;
import com.example.servicesales.entities.SalesDTO;
import com.example.servicesales.services.ISalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/GetVentasid/{customerId}/{productosId}")
    public ResponseEntity <List<SalesDTO>> getByIdCustomersAndProducts(@PathVariable Long customerId, @PathVariable Long productosId){
            List<SalesDTO> ventas = salesService.getByIdCustomersAndProducts(customerId , productosId);
        if(ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(ventas);
        }
    }

}
