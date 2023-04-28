package com.example.demo.DTO;

import com.example.demo.model.enumeration.BookCategory;
import jdk.jfr.Category;
import lombok.Data;

@Data
public class BookDTO {

    private String name;

    private BookCategory category;

    private Long author;

    private Integer availableCopies;

    public BookDTO(String name, BookCategory category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}