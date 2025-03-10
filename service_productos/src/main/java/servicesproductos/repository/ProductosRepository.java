package servicesproductos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import servicesproductos.entities.Productos;

@Repository
public interface ProductosRepository extends CrudRepository<Productos, Long> {
}
