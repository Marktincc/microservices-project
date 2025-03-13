    package servicecustomers.repository;


    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;
    import servicecustomers.entities.Customers;

    import java.util.List;
    import java.util.Optional;


    @Repository
    public interface CustomersRepository extends CrudRepository<Customers, Long> {

        Optional<Customers> findByCorreoAndPassword(String correo, String password);

    }
