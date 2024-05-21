CREATE TABLE books (
                       id VARCHAR(36) NOT NULL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       publicationYear INT NOT NULL,
                       publisher VARCHAR(255) NOT NULL,
                       pageCount INT NOT NULL
);
