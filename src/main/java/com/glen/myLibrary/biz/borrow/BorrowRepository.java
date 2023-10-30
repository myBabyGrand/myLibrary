package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.account.AccountRepositoryCustom;
import com.glen.myLibrary.biz.library.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByBorrowerAndReturnedAtIsNull(LibraryMember borrower);
}
