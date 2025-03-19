package com.example.servicesales.entities;


import lombok.*;

@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDTO {

    private Long id;
    private String nombreProducto;
    private Double valor;
    private String nombre;
    private String apellidos;



}
