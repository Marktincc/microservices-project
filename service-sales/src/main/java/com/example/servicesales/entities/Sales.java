package com.example.servicesales.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ventas")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fecha")
    private Date fecha;

    @JsonProperty("valor")
    private Double valor;

    @JsonProperty("cantidad")
    private Integer cantidad;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "id_usuario")
    private Integer idUsuario;

}
