package servicecustomers.services;


import servicecustomers.entities.Customers;

import java.util.List;
import java.util.Map;

public interface ICustomersService {

    List<Customers> getAll ();

    Customers getById (Long id);

    Customers create (Customers category);

    Customers updateCategory (long id, Map<String, Object> dataUpdated);

    void delete (long id);
}
