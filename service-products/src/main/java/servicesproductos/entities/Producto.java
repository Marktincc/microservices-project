package servicesproductos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "nombreProducto", length = 45)
    @JsonProperty("nombreProducto")
    private String nombreProducto;

    @JsonProperty("cantidad")
    private int cantidad;

    @JsonProperty("valor")
    private int valor;

    @Column(name = "proveedor_idProveedor")
    private Long providerId;

    @Column(name = "categoriaId")
    private Long categoriaId;
}