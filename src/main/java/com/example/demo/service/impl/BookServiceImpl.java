package com.example.demo.service.impl;

import com.example.demo.DTO.BookDTO;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private  final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDTO bookDTO) {
        Author author=this.authorRepository.findById(bookDTO.getAuthor()).orElseThrow();
        Book book=new Book(bookDTO.getName(),bookDTO.getCategory(),author,bookDTO.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDTO) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        Author author = this.authorRepository.findById(bookDTO.getAuthor()).orElseThrow();

        book.setAuthor(author);
        book.setName(bookDTO.getName());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setCategory(bookDTO.getCategory());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
       this.bookRepository.deleteById(id);
    }

    public Optional<Book> findByName(String name){
        return this.bookRepository.findByName(name);
    }
}
