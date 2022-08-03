package com.glen.myLibrary.biz.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void save(AccountDTO accountDTO){
        Account newAccount = new Account(accountDTO.getEmail(),accountDTO.getNickname(), accountDTO.getPassword(), accountDTO.getAccountType(), accountDTO.getDescription());
        accountRepository.save(newAccount);
    }
}
