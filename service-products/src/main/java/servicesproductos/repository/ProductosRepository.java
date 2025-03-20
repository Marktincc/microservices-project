package servicesproductos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import servicesproductos.entities.Producto;

import java.util.List;

@Repository
public interface ProductosRepository extends CrudRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);
}
