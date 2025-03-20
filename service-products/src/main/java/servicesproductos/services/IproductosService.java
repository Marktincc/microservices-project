package servicesproductos.services;

import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Producto;

import java.util.List;
import java.util.Map;


public interface IproductosService {

    List<Producto> getAll();    // Para obtener la lista de entidades

    Producto getById (Long id);

    Producto create (Producto producto);

    Producto update (Long id, Map<String, Object> dataUpdated);

    void delete (Long id);

    List<ProductoDTO> getByCategoria(Long categoriaId);
}
