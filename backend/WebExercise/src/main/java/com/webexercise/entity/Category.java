package com.webexercise.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "category_name", unique=true)
    private String categoryName;
}
