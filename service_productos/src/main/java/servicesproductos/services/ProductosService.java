package servicesproductos.services;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import servicesproductos.entities.Productos;
import servicesproductos.repository.ProductosRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ProductosService implements IproductosService {

    @Autowired
    private ProductosRepository repository;


    public List<Productos> getAll() {
        return (List<Productos>) repository.findAll();

    }

}
