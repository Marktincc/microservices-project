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
@NoArgsConstructor  // ✅ Constructor vacío necesario para Jackson
@AllArgsConstructor // ✅ Constructor con todos los campos

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nombre_pro")
    private String nombre_pro;
    @JsonProperty("cantidad")
    private int cantidad;
    @JsonProperty("valor")
    private double valor;
}
