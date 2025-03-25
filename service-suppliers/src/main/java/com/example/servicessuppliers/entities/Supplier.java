package com.example.servicessuppliers.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor  // ✅ Constructor vacío necesario para Jackson
@AllArgsConstructor // ✅ Constructor con todos los campos
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("correo")
    private String correo;

    //ID PRODUCTO
}
