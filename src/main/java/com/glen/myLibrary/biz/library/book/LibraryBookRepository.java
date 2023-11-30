package com.glen.myLibrary.biz.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryBookRepository  extends JpaRepository<LibraryBook, Long> {
}
