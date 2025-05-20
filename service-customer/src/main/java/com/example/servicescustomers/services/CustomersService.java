package com.example.servicescustomers.services;

import com.example.commonutils.security.jwt.JwtUtil;
import com.example.commonutils.security.encryptPassword.PasswordUtil;
import com.example.servicescustomers.entities.Rol;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.servicescustomers.entities.Customers;
import com.example.servicescustomers.repository.CustomersRepository;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CustomersService implements ICustomersService {

    @Autowired
    private CustomersRepository repository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordUtil passwordUtil;

    public List<Customers> getAll() {
        return (List<Customers>) repository.findAll();
    }


    public Customers getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public Customers create(Customers Customers) {
        convertStringsToUpper(Customers);

        //Encriptar contraseña
        String rawPassword = Customers.getPassword();
        Customers.setPassword(passwordUtil.encrypt(rawPassword));

        return repository.save(Customers);
    }

    public Customers updateCustomers(long id, @NotNull Map<String, Object> dataUpdated) {
        Customers customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        dataUpdated.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customers.class, key);
            if (field != null) {
                field.setAccessible(true);

                // Special handling for rol field
                if (key.equals("rol") && value instanceof String) {
                    try {
                        // Convert string to enum
                        Rol rolEnum = Rol.valueOf((String) value);
                        ReflectionUtils.setField(field, customer, rolEnum);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Invalid role value: " + value);
                    }
                } else if (key.equals("password") && value instanceof String){

                    // Encriptar contraseña
                    String rawPassword = (String) value;
                    String encryptedPassword = passwordUtil.encrypt(rawPassword);
                    ReflectionUtils.setField(field, customer, encryptedPassword);
                }else{
                    ReflectionUtils.setField(field, customer, value);
                }
            }
        });

        return repository.save(customer);
    }
    @Override
    public void delete(long id) {
        Customers customers = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        repository.delete(customers);
    }



    @Override
    public Map<String, String> login(String correo, String password) {
        Customers customer = repository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // Comparar contraseña usando BCrypt
        if (!passwordUtil.matches(password, customer.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (Boolean.FALSE.equals(customer.getEstado())) {
            throw new RuntimeException("Cuenta inactiva. Contacte al administrador.");
        }

        String token = jwtUtil.generarToken(customer.getNombre());

        return Map.of(
                "rol", customer.getRol().toString(),
                "nombre", customer.getNombre(),
                "id", customer.getId().toString(),
                "token", token
        );
    }


    // Método para convertir los Strings en mayúsculas antes de guardar
    private void convertStringsToUpper(Customers customer) {
        if (customer.getNombre() != null) {
            customer.setNombre(customer.getNombre().toUpperCase());
        }
        if (customer.getApellidos() != null) {
            customer.setApellidos(customer.getApellidos().toUpperCase());
        }
        if (customer.getDireccion() != null) {
            customer.setDireccion(customer.getDireccion().toUpperCase());
        }
    }
}
