package com.example.demo.service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.enumeration.BookCategory;
import jdk.jfr.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDTO bookDTO);
    Optional<Book> edit(Long id, BookDTO bookDTO);
    void deleteById(Long id);
    Optional<Book> findByName(String name);
}
