package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.account.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
