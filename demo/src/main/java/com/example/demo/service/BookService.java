package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.dao.JdbcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final JdbcBookRepository bookRepository;

    @Autowired
    public BookService(JdbcBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int addBook(Book book) {
       return bookRepository.addBook(book);
    }

    public Book getBookById(UUID id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void deleteBookById(UUID id) {
        bookRepository.deleteBookById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAllBooks();
    }

    public boolean updateBook(UUID id, Book updatedBook) {
        return bookRepository.updateBook(id, updatedBook);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    public List<Book> getBooksByPublisher(String publisher) {
        return bookRepository.getBooksByPublisher(publisher);
    }
}
