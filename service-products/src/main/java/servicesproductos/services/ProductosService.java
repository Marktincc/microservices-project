package servicesproductos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import servicesproductos.entities.CategoriaDTO;
import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Producto;
import servicesproductos.repository.ProductosRepository;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductosService implements IproductosService {

    @Autowired
    private ProductosRepository repository;

    public List<Producto> getAll() {
        return (List<Producto>) repository.findAll();
    }

    public Producto getById (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto create (Producto producto) {
        return repository.save(producto);
    }

    public Producto update (Long id, Map<String, Object> newData) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        newData.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Producto.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, producto, value);
            }
        });
        return repository.save(producto);
    }

    public void delete (Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        repository.delete(producto);
    }

    // HACER LLAMADO DE PRODUCTOS POR CATEGORIAS CONEXION CON OTRO MICROSERVICIO
    private final RestTemplate restTemplate;

    @Value("${service.categorias.url}") // URL del microservicio de categorías
    private String categoriasServiceUrl;

    public ProductosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductoDTO> getByCategoria(Long categoriaId) {
        List<Producto> productos = repository.findByCategoriaId(categoriaId);

        CategoriaDTO categoria = restTemplate.getForObject(categoriasServiceUrl + "/categorias/getById/" + categoriaId, CategoriaDTO.class);

        return productos.stream().map(producto -> ProductoDTO.builder().id(producto.getId()).nombre(producto.getNombreProducto()).valor(producto.getValor()).categoriaNombre(categoria.getName()).build()).collect(Collectors.toList());
    }
}
