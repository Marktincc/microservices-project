package com.example.servicesales.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO
{
    private Long id;
    private String nombre;
    private String apellidos;
}
