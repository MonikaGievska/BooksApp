package com.example.demo.service;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDTO authorDTO);
    void deleteById(Long id);
}
