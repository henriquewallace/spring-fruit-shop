package com.jeff.springfruitshop.services;

import com.jeff.springfruitshop.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String  name);
}
