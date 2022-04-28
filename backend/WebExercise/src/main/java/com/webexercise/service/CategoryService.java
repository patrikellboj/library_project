package com.webexercise.service;

import com.webexercise.entity.Category;
import com.webexercise.entity.LibraryItem;
import com.webexercise.repository.CategoryRepository;
import com.webexercise.repository.LibraryItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private LibraryItemRepository libraryItemRepository;

    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository,
            LibraryItemRepository libraryItemRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.libraryItemRepository = libraryItemRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Integer> getIdOfDeletableCategories() {
        List<Category> categories = categoryRepository.findAll();
        Iterable<LibraryItem> libraryItems = libraryItemRepository.findAll();

        // Add Category IDs which have a LibraryItem referencing it in a hashset.
        // A hashset (with no duplicates), since a Category can be referenced by many LibraryItems
        HashSet<Integer> categoriesWithReferencedItems = new HashSet<>();
        libraryItems.forEach(e -> categoriesWithReferencedItems.add(e.getCategory().getId()));

        // Remove the Categories which have a reference to LibraryItems from the categories list.
        // categories list now contains only categories which can be deleted.
        // Add the IDs of the categories that can be deleted to deletableCategoriesById
        categoriesWithReferencedItems.forEach(c -> categories.removeIf(e -> Integer.valueOf(e.getId()).equals(c)));
        List<Integer> deletableCategoriesById = new ArrayList<>();
        categories.forEach(c -> deletableCategoriesById.add(c.getId()));
        return deletableCategoriesById;
    }

    public Category saveCategory(Category category) {
        //Should throw exception if category already exists
        return categoryRepository.save(category);
    }

    public String deleteCategory(int id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }

    public Category getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public Category editCategory(int id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}
