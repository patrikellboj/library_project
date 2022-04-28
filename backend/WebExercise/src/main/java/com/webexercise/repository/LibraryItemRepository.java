package com.webexercise.repository;

import com.webexercise.entity.LibraryItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryItemRepository extends PagingAndSortingRepository<LibraryItem, Integer> {
}
