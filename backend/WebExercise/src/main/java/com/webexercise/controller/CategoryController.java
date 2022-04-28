package com.webexercise.controller;

import com.webexercise.entity.Category;
import com.webexercise.service.CategoryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@NoArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = service.getCategories();
        return new ResponseEntity<>(categories, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/deletable")
    public List<Integer> getDeletableCategories() {
        return service.getIdOfDeletableCategories();
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        return service.deleteCategory(id);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") int id) {
            return service.getCategoryById(id);
    }

    @PutMapping("/edit/{id}")
    public Category editCategory(
            @PathVariable("id") int id,
            @RequestBody Category category
    ) {
        return service.editCategory(id, category);
    }

}
