package servicecustomers.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import servicecustomers.entities.Customers;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {
}
