package com.example.memorydb.service;

import com.example.memorydb.book.entity.BookEntity;
import com.example.memorydb.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // create, update
    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }

    // all
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    // delete
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    // findOne
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

}
