package com.example.servicecategories.controllers;

import com.example.servicecategories.entities.Category;
import com.example.servicecategories.repository.CategoryRepository;
import com.example.servicecategories.services.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @GetMapping("/getAll")
    public List<Category> getAll() {return service.getAll();}

    @GetMapping("/getById/{id}")
    public Category getById (@PathVariable long id) {return service.getById(id);}

    @PostMapping("/create")
    public ResponseEntity<Category> create (@RequestBody Category category) {
        Category categoryCreated = service.create(category);
        return ResponseEntity.status(201).body(categoryCreated);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Category> update (@PathVariable Long id, @RequestBody Map<String, Object> dataUpdated) {
        Category categoryUpdated = service.updateCategory(id, dataUpdated);
        return ResponseEntity.ok(categoryUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Categoria eliminada correctamente");
    }
}
