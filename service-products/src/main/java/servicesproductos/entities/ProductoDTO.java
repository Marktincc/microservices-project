package servicesproductos.entities;


import lombok.*;

@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double valor;
    private String categoriaNombre;
}