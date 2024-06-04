-- Tábla létrehozása
CREATE TABLE IF NOT EXISTS BOOK (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL
);

-- Tesztadatok beszúrása
INSERT INTO BOOK (title, author, isbn) VALUES ('Effective Java', 'Joshua Bloch', '978-0134685991');
INSERT INTO BOOK (title, author, isbn) VALUES ('Clean Code', 'Robert C. Martin', '978-0132350884');
INSERT INTO BOOK (title, author, isbn) VALUES ('Spring in Action', 'Craig Walls', '978-1617294945');
