package com.jeff.springfruitshop.controllers.v1;

import com.jeff.springfruitshop.api.v1.model.CategoriesListDTO;
import com.jeff.springfruitshop.api.v1.model.CategoryDTO;
import com.jeff.springfruitshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoriesListDTO> getAllCategories() {
        return new ResponseEntity<CategoriesListDTO>(
                new CategoriesListDTO(categoryService.getAllCategories()), HttpStatus.OK
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<CategoryDTO>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }
}
