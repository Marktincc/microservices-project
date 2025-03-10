package servicesproductos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import servicesproductos.entities.Productos;

import java.util.List;

@Repository
public interface ProductosRepository extends CrudRepository<Productos, Long> {

    List<Productos> findByCategoriaId(Long categoriaId);
}
