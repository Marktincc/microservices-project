package com.example.servicecategories.services;

import com.example.servicecategories.entities.Category;
import com.example.servicecategories.repository.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository repository;


    public List<Category> getAll() {return (List<Category>) repository.findAll();}

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    public Category create(Category category) {return repository.save(category);}

    public Category updateCategory (long id, @NotNull Map<String, Object> dataUpdated) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        dataUpdated.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Category.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, category, value);
            }
        });
        return repository.save(category);
    }

    public void delete (long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        repository.delete(category);
    }
}
