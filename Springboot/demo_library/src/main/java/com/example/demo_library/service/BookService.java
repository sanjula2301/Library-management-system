package com.example.demo_library.service;

import com.example.demo_library.entity.Book;
import com.example.demo_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public void updateBook(Book updatedBook) {
        bookRepository.save(updatedBook);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }


}

