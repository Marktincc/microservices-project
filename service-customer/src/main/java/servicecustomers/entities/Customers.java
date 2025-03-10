package servicecustomers.entities;

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
    @JsonProperty("rol")
    private String rol;
    @JsonProperty("contraseña_hash")
    private String contraseña_hash;

}
