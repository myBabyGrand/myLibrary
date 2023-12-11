package com.glen.myLibrary.biz.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn10(String isbn10);

    Optional<Book> findByIsbn13(String isbn13);
}
