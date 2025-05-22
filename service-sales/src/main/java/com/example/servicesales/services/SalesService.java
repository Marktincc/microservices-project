package com.example.servicesales.services;

import com.example.servicesales.entities.Sales;
import com.example.servicesales.repository.SalesRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private SalesRepository repository;

    public List<Sales> getAll() {
        return (List<Sales>) repository.findAll();
    }

    public Sales getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public Sales create(Sales sale) {

        return repository.save(sale);
    }


    public Sales updateSale(long id, @NotNull Map<String, Object> dataUpdated) {
        Sales sale = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        dataUpdated.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Sales.class, key);
            if (field != null) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                if (fieldType.equals(LocalDateTime.class) && value instanceof String stringValue) {
                    try {
                        value = LocalDateTime.parse(stringValue, formatter);
                    } catch (Exception e) {
                        throw new RuntimeException("Formato de fecha inválido para campo: " + key);
                    }
                } else if (fieldType.equals(String.class) && value instanceof String stringValue) {
                    value = stringValue.toUpperCase();
                } else if (fieldType.equals(Integer.class)) {
                    if (value instanceof Number numVal) {
                        value = numVal.intValue();
                    } else if (value instanceof String strVal) {
                        try {
                            value = Integer.parseInt(strVal);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Valor inválido para campo Integer: " + key);
                        }
                    }
                } else if (fieldType.equals(Double.class)) {
                    if (value instanceof Number numVal) {
                        value = numVal.doubleValue();
                    } else if (value instanceof String strVal) {
                        try {
                            value = Double.parseDouble(strVal);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Valor inválido para campo Double: " + key);
                        }
                    }
                }

                ReflectionUtils.setField(field, sale, value);
            }
        });


        return repository.save(sale);
    }
    @Override
    public void delete(long id) {

    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> getAllPrueba() {
        String sql = "call get_sale_info()"; // Llama al procedimiento almacenado
        return jdbcTemplate.queryForList(sql);
    }
}
