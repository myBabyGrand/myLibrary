package com.glen.myLibrary.biz.account;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void clean(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("AccountService - save")
    void saveTest(){
        //given
        AccountDTO accountDTO = AccountDTO.builder()
                .nickname("test닉네임")
                .email("testEmail@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description")
                .build();

        //when
        service.save(accountDTO);

        //then
        assertEquals(1L, repository.count());
        Account account = repository.findAll().get(0);
        assertEquals(account.getNickname(), accountDTO.getNickname());
    }



    @Test
    @DisplayName("AccountService - get")
    void getTest(){
        //given
        Account account = Account.builder()
                .nickname("test닉네임")
                .email("testEmail@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description")
                .build();
        repository.save(account);

        //when
        Account getAccount = service.get(account.getId());


        //then
        assertNotNull(getAccount);
        assertEquals(getAccount.getNickname(), account.getNickname());
    }

}