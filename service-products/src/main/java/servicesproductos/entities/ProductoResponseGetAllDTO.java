package servicesproductos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductoResponseGetAllDTO {

    private Long id;
    private String nombreProducto;
    private int cantidad;
    private int valor;
    private String categoriaNombre;
    private String proveedorNombre;

}
