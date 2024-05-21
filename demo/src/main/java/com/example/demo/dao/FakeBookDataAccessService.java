package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Qualifier("fakeDao")
public class FakeBookDataAccessService implements BookDao {

    private static final List<Book> DB = new ArrayList<>();

    @Override
    public int addBook(UUID id,Book book) {
        DB.add(new Book(id, book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getPublisher(), book.getPageCount()));
        return 1;
    }

    @Override
    public List<Book> getAllBooks() {
        return DB;
    }

    @Override
    public Book getBookById(UUID id) {
        return DB.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteBookById(UUID id) {
        DB.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public void deleteAllBooks() {
        DB.clear();
    }

    @Override
    public boolean updateBook(UUID id, Book updatedBook) {
        return DB.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(book -> {
                    int index = DB.indexOf(book);
                    DB.set(index, updatedBook);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : DB) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> booksByPublisher = new ArrayList<>();
        for (Book book : DB) {
            if (book.getPublisher().equals(publisher)) {
                booksByPublisher.add(book);
            }
        }
        return booksByPublisher;
    }
}
