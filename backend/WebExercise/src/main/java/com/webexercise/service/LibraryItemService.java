package com.webexercise.service;

import com.webexercise.entity.LibraryItem;
import com.webexercise.repository.LibraryItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class LibraryItemService {

    private LibraryItemRepository repository;

    @Autowired
    public LibraryItemService(LibraryItemRepository repository) {
        this.repository = repository;
    }
    
    public List<LibraryItem> getItems(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<LibraryItem> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            pagedResult.getContent().forEach(e -> {
                String title = e.getTitle();
                // Convert String to stream. Trim trailing and leading spaces, split the words where there are space.
                // Map through every word, get the character at index 0 for that word.
                // Take that character and to an upperCase String.
                // Join the Stream to a String
                String titleAcronym = Arrays.stream(title.trim().split(" "))
                        .map(word -> word.charAt(0))
                        .map(c -> c.toString().toUpperCase())
                        .collect(Collectors.joining());
//                String titleAcronym = title.replaceAll("\\B.|\\P{L}", "").toUpperCase();
                e.setTitle(title + " (" + titleAcronym + ")");
                System.out.println(e.getTitle());
            });
            return pagedResult.getContent();
        }
        return new ArrayList<>();
    }

    public ResponseEntity<LibraryItem> saveLibraryItem(LibraryItem libraryItem) {
        switch (libraryItem.getType()) {
            case REFERENCE_BOOK : {
                libraryItem.setBorrowable(false);
                break;
            }
            default: libraryItem.setBorrowable(true);
        }
        LibraryItem savedItem = repository.save(libraryItem);

        return new ResponseEntity<>(savedItem, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteLibraryItem(int id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Item deleted", new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<LibraryItem> editLibraryItem(int id, boolean checkedOut, LibraryItem libraryItem) {
            LibraryItem existingLibraryItem = repository.findById(id).orElse(null);
        try {

            existingLibraryItem.setCategory(libraryItem.getCategory());
            existingLibraryItem.setTitle(libraryItem.getTitle());
            existingLibraryItem.setType(libraryItem.getType());
            existingLibraryItem.setRunTimeMinutes(libraryItem.getRunTimeMinutes());
            existingLibraryItem.setAuthor(libraryItem.getAuthor());
            existingLibraryItem.setPages(libraryItem.getPages());
            if (checkedOut && !Objects.equals(existingLibraryItem.getBorrower(), libraryItem.getBorrower())) {
                existingLibraryItem.setBorrower(libraryItem.getBorrower());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                existingLibraryItem.setBorrowDate(dtf.format((now)));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return saveLibraryItem(existingLibraryItem);
    }
}
