    package com.example.servicescustomers.repository;


    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;
    import com.example.servicescustomers.entities.Customers;

    import java.util.Optional;


    @Repository
    public interface CustomersRepository extends CrudRepository<Customers, Long> {

        Optional<Customers> findByCorreoAndPassword(String correo, String password);

    }
