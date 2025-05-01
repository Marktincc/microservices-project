package servicesproductos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicesproductos.entities.ProductoDTO;
import servicesproductos.entities.Producto;
import servicesproductos.entities.ProductoResponseGetAllDTO;
import servicesproductos.services.IproductosService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final IproductosService productosService;

    public ProductosController(IproductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping("/getAll")
    public List<Producto> getAll() {
        return productosService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Producto getById(@PathVariable Long id) {
        return productosService.getById(id);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> getByCategoria(@PathVariable Long id) {
        List<ProductoDTO> productos = productosService.getByCategoria(id);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/create")
    public ResponseEntity<Producto> createProduct (@RequestBody Producto newProduct) {
        Producto productCreated = productosService.create(newProduct);
        return ResponseEntity.status(201).body(productCreated);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Producto> updateProduct (@PathVariable Long id, @RequestBody Map<String, Object> dataUpdated){
        Producto product = productosService.update(id, dataUpdated);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct (@PathVariable Long id) {
        productosService.delete(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
    @GetMapping("/getAll1")
    public List<ProductoResponseGetAllDTO> getAllProductosConNombres() {
        return productosService.getAllProductosConNombres();
    }

    @DeleteMapping("/deleteByProveedor/{proveedorId}")
    public ResponseEntity<Void> deleteByProveedor(@PathVariable Long proveedorId) {
        productosService.deleteByProveedor(proveedorId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
    @DeleteMapping("/deleteByCategoria/{categoriaId}")
    public ResponseEntity<Void> deleteByCategoria(@PathVariable Long categoriaId) {
        productosService.deleteByCategoria(categoriaId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
