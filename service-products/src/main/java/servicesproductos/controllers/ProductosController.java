package servicesproductos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Productos;
import servicesproductos.services.IproductosService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final IproductosService productosService;

    public ProductosController(IproductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping("/getAll")
    public List<Productos> getAll() {
        return productosService.getAll();
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> getByCategoria(@PathVariable Long id) {
        List<ProductoDTO> productos = productosService.getByCategoria(id);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
