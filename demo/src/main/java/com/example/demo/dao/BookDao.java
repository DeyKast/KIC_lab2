package com.example.demo.dao;

import com.example.demo.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookDao {
    // Додавання книги
    int addBook(UUID id,Book book);

    default int addBook(Book book) {
        UUID id = UUID.randomUUID();
        return addBook(id, book);
    }
    // Перегляд книги за її id
    Book getBookById(UUID id);

    // Перегляд повного переліку книг
    List<Book> getAllBooks();

    // Видалення книги за її id
    void deleteBookById(UUID id);

    // Видалення повного переліку книг
    void deleteAllBooks();

    // Модифікація книги за її id
    boolean updateBook(UUID id, Book updatedBook);

    // Перегляд книг за автором
    List<Book> getBooksByAuthor(String author);

    // Перегляд книг за видавництвом
    List<Book> getBooksByPublisher(String publisher);
}
