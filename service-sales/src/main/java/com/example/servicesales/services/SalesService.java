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
import java.util.stream.Collectors;

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

        List<Sales> sales = repository.findByCustomerIdAndIdProducto(customerId , productosId);

        CustomersDTO customers = restTemplate.getForObject(salesServiceUrl + "/usuarios/getById/" + customerId,
                CustomersDTO.class);

        ProductsDTO products = restTemplate.getForObject(
                salesServiceUrl + "/productos/categoria/" + productosId, ProductsDTO.class
        );
        return sales.stream()
                .map (sale -> SalesDTO.builder()
                        .id(sale.getId())
                        .valor(sale.getValor())
                        .nombre(customers.getNombre())
                        .apellidos(customers.getApellidos())
                        .nombreProducto(products.getNombreProducto())
                        .build())
                .collect(Collectors.toList());
    }
}
