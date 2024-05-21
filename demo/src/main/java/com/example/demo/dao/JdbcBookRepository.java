package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JdbcBookRepository implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addBook(UUID id, Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (id, title, author, publicationYear, publisher, pageCount) VALUES (?, ?, ?, ?, ?, ?)",
                id.toString(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getPublisher(), book.getPageCount());
        return 1;
    }

    @Override
    public Book getBookById(UUID id) {
        String idAsString = id.toString();
        List<Book> result = jdbcTemplate.query(
                "SELECT id, title, author, publicationYear, publisher, pageCount FROM books WHERE id = ?",
                new Object[]{idAsString},
                (rs, rowNum) -> {
                    UUID bookId = UUID.fromString(rs.getString("id"));
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    Integer publicationYear = rs.getInt("publicationYear");
                    String publisher = rs.getString("publisher");
                    Integer pageCount = rs.getInt("pageCount");

                    return new Book(bookId, title, author, publicationYear, publisher, pageCount);
                });
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                "SELECT id, title, author, publicationYear, publisher, pageCount FROM books",
                (rs, rowNum) -> {
                    UUID id = UUID.fromString(rs.getString("id"));
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    Integer publicationYear = rs.getInt("publicationYear");
                    String publisher = rs.getString("publisher");
                    Integer pageCount = rs.getInt("pageCount");
                    return new Book(id, title, author, publicationYear, publisher, pageCount);
                });
    }

    @Override
    public void deleteBookById(UUID id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id.toString());
    }

    @Override
    public void deleteAllBooks() {
        jdbcTemplate.update("DELETE FROM books");
    }

    @Override
    public boolean updateBook(UUID id, Book updatedBook) {
        String idAsString = id.toString();
        int rowsUpdated = jdbcTemplate.update(
                "UPDATE books SET title = ?, author = ?, publicationYear = ?, publisher = ?, pageCount = ? WHERE id = ?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublicationYear(),
                updatedBook.getPublisher(), updatedBook.getPageCount(), idAsString);


        return rowsUpdated > 0;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return jdbcTemplate.query(
                "SELECT id, title, author, publicationYear, publisher, pageCount FROM books WHERE author = ?",
                new Object[]{author},
                (rs, rowNum) -> {
                    UUID id = UUID.fromString(rs.getString("id"));
                    String title = rs.getString("title");
                    String dbAuthor = rs.getString("author");
                    Integer publicationYear = rs.getInt("publicationYear");
                    String publisher = rs.getString("publisher");
                    Integer pageCount = rs.getInt("pageCount");

                    return new Book(id, title, dbAuthor, publicationYear, publisher, pageCount);
                });
    }


    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return jdbcTemplate.query(
                "SELECT id, title, author, publicationYear, publisher, pageCount FROM books WHERE publisher = ?",
                new Object[]{publisher},
                (rs, rowNum) -> {
                    UUID id = UUID.fromString(rs.getString("id"));
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    Integer publicationYear = rs.getInt("publicationYear");
                    String dbPublisher = rs.getString("publisher");
                    Integer pageCount = rs.getInt("pageCount");

                    return new Book(id, title, author, publicationYear, dbPublisher, pageCount);
                });
    }
}
