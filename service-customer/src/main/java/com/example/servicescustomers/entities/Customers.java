package com.example.servicescustomers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellidos")
    private String apellidos;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("telefono")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @JsonProperty("rol")
    private Rol rol;
    @JsonProperty("password")
    private String password;
    @JsonProperty("estado")
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean estado = Boolean.TRUE;

}
