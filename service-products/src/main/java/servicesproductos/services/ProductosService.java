package servicesproductos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import servicesproductos.entities.*;
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

    public Producto getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto create(Producto producto) {
        return repository.save(producto);
    }

    public Producto update(Long id, Map<String, Object> newData) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        newData.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Producto.class, key);
            if (field != null) {
                field.setAccessible(true);


                if ("categoriaId".equals(key) && value instanceof String) {
                    value = Integer.parseInt((String) value);
                }
                if ("providerId".equals(key) && value instanceof String) {
                    value = Integer.parseInt((String) value);
                }
                if ("cantidad".equals(key) && value instanceof String) {
                    value = Integer.parseInt((String) value);
                }

                ReflectionUtils.setField(field, producto, value);
            }
        });

        return repository.save(producto);
    }


    public void delete(Long id) {
        Producto producto = repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

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

    //LLAMAR PRODUCTO CON NOMBRE DE CATEGORIA Y NOMBRE DE PROVEEDOR

    @Value("${service.proveedores.url}")
    private String proveedoresServiceUrl;

    public List<ProductoResponseGetAllDTO> getAllProductosConNombres() {
        List<Producto> productos = (List<Producto>) repository.findAll();

        return productos.stream().map(producto -> {
            // Llamar microservicio categoría
            CategoriaDTO categoria = restTemplate.getForObject(categoriasServiceUrl + "/categorias/getById/" + producto.getCategoriaId(), CategoriaDTO.class);

            // Llamar microservicio proveedor
            ProveedorDTO proveedor = restTemplate.getForObject(proveedoresServiceUrl + "/proveedores/getById/" + producto.getProviderId(), ProveedorDTO.class);

            return ProductoResponseGetAllDTO.builder().id(producto.getId()).nombreProducto(producto.getNombreProducto()).cantidad(producto.getCantidad()).valor(producto.getValor()).categoriaNombre(categoria != null ? categoria.getName() : "Sin categoría").proveedorNombre(proveedor != null ? proveedor.getNombre() : "Sin proveedor").build();
        }).collect(Collectors.toList());
    }



    // Método para eliminar productos por proveedor
    public void deleteByProveedor(Long proveedorId) {
        // Buscamos todos los productos asociados al proveedor
        List<Producto> productos = repository.findByProviderId(proveedorId);

        // Si existen productos, los eliminamos
        if (!productos.isEmpty()) {
            repository.deleteAll(productos); // Elimina los productos de la base de datos
        }
    }

    // Método para eliminar productos por categoria
    public void deleteByCategoria(Long categoriaId) {
        // Buscamos todos los productos asociados al proveedor
        List<Producto> productos = repository.findByCategoriaId(categoriaId);

        // Si existen productos, los eliminamos
        if (!productos.isEmpty()) {
            repository.deleteAll(productos); // Elimina los productos de la base de datos
        }
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> getAllPrueba() {
        String sql = "call get_product_info()"; // Llama al procedimiento almacenado
        return jdbcTemplate.queryForList(sql);
    }
}
