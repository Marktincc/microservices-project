package com.example.servicesuppliers.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor  // ✅ Constructor vacío necesario para Jackson
@AllArgsConstructor // ✅ Constructor con todos los campos
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
}
