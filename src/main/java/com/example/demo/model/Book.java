package com.example.demo.model;

import com.example.demo.model.enumeration.BookCategory;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private BookCategory category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book(){
    }
    public Book(String name){
        this.name=name;
    }


    public Book(String name, BookCategory category, Author author, Integer copies) {
        this.name=name;
        this.category=category;
        this.author=author;
        this.availableCopies=copies;

    }
}
