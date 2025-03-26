package com.example.servicesales.services;

import com.example.servicesales.entities.CustomersDTO;
import com.example.servicesales.entities.ProductsDTO;
import com.example.servicesales.entities.Sales;
import com.example.servicesales.entities.SalesDTO;
import com.example.servicesales.repository.ISalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService implements ISalesService{

    @Autowired
    private ISalesRepository repository;

    public List<Sales> getAll() {
        return (List<Sales>) repository.findAll();
    }


    private final RestTemplate restTemplate;

    @Value("${service.sales.url}")
    private String salesServiceUrl;

    private SalesService (RestTemplate restTemplate){ this.restTemplate = restTemplate;}


        public List <SalesDTO> getByIdCustomersAndProducts(Long customerId , Long productosId)
    {

        List<Sales> sales1 = repository.findByCustomerId(customerId);

        CustomersDTO customers = restTemplate.getForObject(salesServiceUrl + "/usuarios/getById/" + customerId,
                CustomersDTO.class);

        List<Sales> sales = repository.findByProductosId(productosId);

        ProductsDTO products = restTemplate.getForObject(
                salesServiceUrl + "/productos/categoria/" + productosId, ProductsDTO.class
        );

        return sales.stream()
                .map (sale -> SalesDTO.builder()
                        .id(Sales.getId())
                        .valor(Sales.getValor())
                        .nombre(customers.getNombre())
                        .apellidos(customers.getApellidos())
                        .nombreProducto(products.getNombreProducto())

                )


    }
}
