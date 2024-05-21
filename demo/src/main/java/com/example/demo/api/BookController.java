package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")

public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addBook(@Valid @NonNull @RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllBooks() {
        bookService.deleteAllBooks();
        return ResponseEntity.ok("All books deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable UUID id, @RequestBody Book updatedBook) {
        boolean updated = bookService.updateBook(id, updatedBook);
        if (updated) {
            return ResponseEntity.ok("Book updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update book with id " + id);
        }
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/publisher/{publisher}")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable String publisher) {
        List<Book> books = bookService.getBooksByPublisher(publisher);
        return ResponseEntity.ok(books);
    }
}
