package servicesproductos.services;



import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Productos;

import java.util.List;


public interface IproductosService {

    List<Productos> getAll();    // Para obtener la lista de entidades
    List<ProductoDTO> getByCategoria(Long categoriaId);
}
