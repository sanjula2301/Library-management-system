package com.example.demo_library.controller;

import com.example.demo_library.entity.Book;
import com.example.demo_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping("/form")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @PostMapping("/postBook")
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.createBook(book);
        return "redirect:/books/getBookList";
    }

    @GetMapping("/getBookList")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookList";
    }




    @GetMapping("/getBook")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }




    @PutMapping("/updateBook")
    public void updateBook(@RequestBody Book updatedBook) {
        bookService.updateBook(updatedBook);
    }




    @GetMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books/getBookList";
    }


}

