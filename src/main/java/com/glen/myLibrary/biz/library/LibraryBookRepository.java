package com.glen.myLibrary.biz.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryBookRepository  extends JpaRepository<LibraryBook, Long> {
}
