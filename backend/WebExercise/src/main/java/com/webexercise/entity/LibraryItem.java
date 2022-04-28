package com.webexercise.entity;

import com.webexercise.enums.LibraryItemType;
import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "library_item")
public class LibraryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // (fetch = FetchType.EAGER) You want to fetch category everytime you fetch libraryitem
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "`type`")
    @Enumerated(EnumType.STRING)
    private LibraryItemType type;

    @Column(nullable = false, columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean borrowable;

    @Column(name = "borrow_date")
    private String borrowDate;

    @Column(columnDefinition = "integer default null", name = "run_time_minutes")
    private Integer runTimeMinutes;

    private String author;
    private Integer pages;
    private String borrower;


}