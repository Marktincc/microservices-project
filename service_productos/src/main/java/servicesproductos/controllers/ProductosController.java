package servicesproductos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicesproductos.entities.Productos;
import servicesproductos.repository.ProductosRepository;
import servicesproductos.services.IproductosService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private IproductosService service;

    @Autowired
    private ProductosRepository customerRepository;

    @GetMapping("/getAll")
    public List<Productos> getAll() {
        return service.getAll();
    }





}
