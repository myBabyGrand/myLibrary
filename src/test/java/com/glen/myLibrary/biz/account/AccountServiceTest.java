package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.account.dto.AccountDTO;
import com.glen.myLibrary.biz.account.dto.AccountUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    private static Account createTestAccount() {
        return Account.builder()
                .nickname("test닉네임")
                .email("testEmail@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description")
                .build();
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
        Account account = createTestAccount();
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

    @Test
    @DisplayName("AccountService.updateAccount")
    void updateAccountTest(){
        //given
        Account account = createTestAccount();

        repository.save(account);

        AccountUpdateDTO accountUpdateDTO = AccountUpdateDTO.builder()
                .nickname("0"+account.getNickname())
                .email("0"+account.getEmail())
                .accountType(AccountType.LIBRARY_ADMIN)
                .password("0"+account.getPassword())
                .description("0"+account.getDescription())
                .emailVerified(account.isEmailVerified())
                .emailVerifiedAt(LocalDateTime.MAX)
                .build();


        //when
        service.updateAccount(account.getId(), accountUpdateDTO);

        //then
        Account findAccount = repository.findById(account.getId()).orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + account.getId()));
        assertEquals(findAccount.getNickname(), accountUpdateDTO.getNickname());
        assertEquals(findAccount.getAccountType(), accountUpdateDTO.getAccountType());
    }

    @Test
    @DisplayName("AccountService.updateAccount : null인 업데이터 항목이 있는 경우")
    void updateAccountTest_nullCase(){
        //given
        Account account = createTestAccount();

        repository.save(account);

        //누락된 항목 : accountType,  emailVerified
        AccountUpdateDTO accountUpdateDTO = AccountUpdateDTO.builder()
                .nickname("0"+account.getNickname())
                .email("0"+account.getEmail())
                .password("0"+account.getPassword())
                .description("0"+account.getDescription())
                .emailVerifiedAt(LocalDateTime.MAX)
                .build();


        //when
        service.updateAccount(account.getId(), accountUpdateDTO);

        //then
        Account findAccount = repository.findById(account.getId()).orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + account.getId()));
        //수정된 항목
        assertEquals(findAccount.getNickname(), accountUpdateDTO.getNickname());

        //수정되지 않은 항목
        assertEquals(findAccount.getAccountType(), account.getAccountType());
        assertEquals(findAccount.isEmailVerified(), account.isEmailVerified());
    }



    @Test
    @DisplayName("AccountService.deleteAccount")
    void deleteAccountTest(){
        //given
        Account account = createTestAccount();
        repository.save(account);

        //when
        service.deleteAccount(account.getId());

        //then
        Optional<Account> findById = repository.findById(account.getId());

        assertTrue(findById.isEmpty());
    }
}