package com.glen.myLibrary.biz.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

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
    @DisplayName("AccountService.save")
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
    @DisplayName("AccountService.get")
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
        AccountResponse accountResponse = service.get(account.getId());

        //then
        assertNotNull(accountResponse);
        assertEquals(accountResponse.getNickname(), account.getNickname());
    }

    @Test
    @DisplayName("AccountService.getAccountList")
    void getAccountListTest(){
        //given
        Account account1 = Account.builder()
                .nickname("test닉네임1")
                .email("testEmail1@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description1")
                .build();
        repository.save(account1);

        Account account2 = Account.builder()
                .nickname("test닉네임2")
                .email("testEmail2@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description2")
                .build();
        repository.save(account2);

        //when
        List<AccountResponse> accountList = service.getAccountList();

        //then
        assertEquals(2L,accountList.size());
    }

}