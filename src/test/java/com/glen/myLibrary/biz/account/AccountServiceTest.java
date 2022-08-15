package com.glen.myLibrary.biz.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Account> requestAccounts = IntStream.range(1,31)
                .mapToObj(i->{
                    return Account.builder()
                            .nickname("test닉네임"+i)
                            .email("testEmail"+i+"@Email.com")
                            .accountType(AccountType.USER)
                            .password("testPassword"+i)
                            .joinedAt(LocalDateTime.now())
                            .description("test Description"+i)
                            .build();
                }).collect(Collectors.toList());

        repository.saveAll(requestAccounts);

        //when
//        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        AccountSearch accountSearch = AccountSearch.builder()
                .page(1)
                .size(10)
                .build();

        List<AccountResponse> accountList = service.getAccountList(accountSearch);

        //then
        assertEquals(10,accountList.size());
        assertEquals("test닉네임30", accountList.get(0).getNickname());
    }

}