package com.example.demo.service.impl;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.model.Author;
import com.example.demo.model.Country;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDTO authorDTO) {
        Country country = this.countryRepository.findById(authorDTO.getCountry())
                .orElseThrow();

        Author author = new Author(authorDTO.getName(), authorDTO.getSurname(), country);

        this.authorRepository.save(author);
        return Optional.of(author);
    }


    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
