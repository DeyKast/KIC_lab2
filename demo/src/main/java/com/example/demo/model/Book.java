package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Book {
    private  UUID id;
    @NotBlank
    private  String title;
    @NotBlank
    private  String author;
    @NotNull
    private  Integer publicationYear;
    @NotBlank
    private  String publisher;
    @NotNull
    private  Integer pageCount;

    // Додано конструктор за замовчуванням
    public Book() {
        this.id = null;
        this.title = null;
        this.author = null;
        this.publicationYear = null;
        this.publisher = null;
        this.pageCount = null;
    }

    public Book(@JsonProperty("id") UUID id,
                @JsonProperty("title") String title,
                @JsonProperty("author") String author,
                @JsonProperty("publicationYear") Integer publicationYear,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("pageCount") Integer pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPageCount() {
        return pageCount;
    }
}
