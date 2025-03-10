package servicesproductos.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import servicesproductos.entities.CategoriaDTO;
import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Productos;
import servicesproductos.repository.ProductosRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductosService implements IproductosService {


    @Autowired
    private ProductosRepository repository;
    private final RestTemplate restTemplate;


    public List<Productos> getAll() {
        return (List<Productos>) repository.findAll();

    }



    // HACER LLAMADO DE PRODUCTOS POR CATEGORIAS CONEXION CON OTRO MICROSERVICIO


    @Value("${service.categorias.url}") // URL del microservicio de categorías
    private String categoriasServiceUrl;

    public ProductosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductoDTO> getByCategoria(Long categoriaId) {
        List<Productos> productos = repository.findByCategoriaId(categoriaId);

        CategoriaDTO categoria = restTemplate.getForObject(
                categoriasServiceUrl + "/categorias/getById/" + categoriaId,
                CategoriaDTO.class
        );

        return productos.stream()
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombreProducto())
                        .valor(producto.getValor())
                        .categoriaNombre(categoria.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
