package com.example.servicesales.services;

import com.example.servicesales.entities.CustomersDTO;
import com.example.servicesales.entities.ProductsDTO;
import com.example.servicesales.entities.Sales;
import com.example.servicesales.entities.SalesDTO;
import com.example.servicesales.repository.SalesRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private SalesRepository repository;

    public List<Sales> getAll() {
        return (List<Sales>) repository.findAll();
    }


    private final RestTemplate restTemplate;

    @Value("${service.sales.url}")
    private String salesServiceUrl;

    private SalesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<SalesDTO> getByIdCustomersAndProducts(Long customerId, Long productosId) {

        List<Sales> sales = repository.findByCustomerIdAndIdProducto(customerId, productosId);

        CustomersDTO customers = restTemplate.getForObject(salesServiceUrl + "/usuarios/getById/" + customerId,
                CustomersDTO.class);

        ProductsDTO products = restTemplate.getForObject(
                salesServiceUrl + "/productos/categoria/" + productosId, ProductsDTO.class
        );
        return sales.stream()
                .map(sale -> SalesDTO.builder()
                        .id(sale.getId())
                        .valor(sale.getValor())
                        .nombre(customers.getNombre())
                        .apellidos(customers.getApellidos())
                        .nombreProducto(products.getNombreProducto())
                        .build())
                .collect(Collectors.toList());
    }

    public Sales updateSales(long id, @NotNull Map<String, Object> dataUpdated) {
        Sales sales = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        dataUpdated.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Sales.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, sales, value);
            }
        });
        return repository.save(sales);
    }
}