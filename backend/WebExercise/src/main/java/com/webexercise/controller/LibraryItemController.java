package com.webexercise.controller;

import com.webexercise.entity.LibraryItem;
import com.webexercise.service.LibraryItemService;
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
@RequestMapping("/items")
public class LibraryItemController {

    private LibraryItemService service;

    @Autowired
    public LibraryItemController(LibraryItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LibraryItem>> getItems(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "category.categoryName") String sortBy
    ) {
        List<LibraryItem> libraryItems = service.getItems(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(libraryItems, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibraryItem> addItem(@RequestBody LibraryItem libraryItem) {
        return service.saveLibraryItem(libraryItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibraryItem(@PathVariable int id) {
        return service.deleteLibraryItem(id);
    }

    @PutMapping("/edit/{id}/{checkedOut}")
    public ResponseEntity<LibraryItem> editLibraryItem(
            @PathVariable("id") int id,
            @PathVariable("id") boolean checkedOut,
            @RequestBody LibraryItem libraryItem
    ) {
        return service.editLibraryItem(id, checkedOut, libraryItem);
    }
}
