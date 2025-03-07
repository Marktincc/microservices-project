package com.example.servicecategories.services;

import com.example.servicecategories.entities.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryService {

    List<Category> getAll ();

    Category getById (Long id);

    Category create (Category category);

    Category updateCategory (long id, Map<String, Object> dataUpdated);

    void delete (long id);
}
