package com.glen.myLibrary.biz.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glen.myLibrary.biz.account.dto.AccountDTO;
import com.glen.myLibrary.biz.account.dto.AccountUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("AccountController.save")
    void save() throws Exception {

        //given
        AccountDTO accountDTO = AccountDTO.builder()
                .nickname("닉네임1")
                .email("abcd@gmail.com")
                .password("password1")
                .accountType(AccountType.USER)
                .description("본인설명")
                .build();

        String json = objectMapper.writeValueAsString(accountDTO);
        log.info(json);

        //when
        mockMvc.perform(post("/account/save")
                .contentType(APPLICATION_JSON)
                .content(json))
//                .content("{\"nickName\":\"닉네임1\", \"email\":\"abcd@gmail.com\", \"password\":\"password1\", \"accountType\":\"USER\", \"description\":\"설명1\"}"))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1, repository.count());
        for (Account account : repository.findAll()) {
            log.info(account.toString());
            log.info(account.getCreatedBy());
            log.info(String.valueOf(account.getCreatedDate()));
            log.info(account.getLastModifiedBy());
            log.info(String.valueOf(account.getLastModifiedDate()));
        }

    }

    @Test
    @DisplayName("AccountController.get")
    void getTest() throws Exception {
        //given
        Account account = createTestAccount();
        repository.save(account);

        //expected(when & then)
        mockMvc.perform(get("/account/{accountId}", account.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(account.getId()))
                .andExpect(jsonPath("$.nickname").value(account.getNickname()))
                .andDo(print());
    }

    @Test
    @DisplayName("AccountController.getAccounts")
    void getAccountsTest() throws Exception {
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

        //expected(when & then)
        mockMvc.perform(get("/accounts?page=1&sort=id,desc&size=5")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2L))
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$.[0].nickname").value("test닉네임30"))
                .andExpect(jsonPath("$.[1].nickname").value("test닉네임29"))
                .andDo(print());
    }

    @Test
    @DisplayName("AccountController.getAccounts : in case of page is 0")
    void getAccountsTes_page0() throws Exception {
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

        //expected(when & then)
        mockMvc.perform(get("/accounts?page=0&size=5")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2L))
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$.[0].nickname").value("test닉네임30"))
                .andExpect(jsonPath("$.[1].nickname").value("test닉네임29"))
                .andDo(print());
    }

    @Test
    @DisplayName("AccountController.updateAccount")
    void updateAccountTest() throws Exception {
        //given
        Account account = createTestAccount();

        repository.save(account);


        //누락된 항목 : accountType, email, emailVerified
        AccountUpdateDTO accountUpdateDTO = AccountUpdateDTO.builder()
                .nickname("0"+account.getNickname())
                .email("0"+account.getEmail())
                .password("0"+account.getPassword())
                .description("0"+account.getDescription())
                .emailVerifiedAt(LocalDateTime.MAX)
                .build();

        String json = objectMapper.writeValueAsString(accountUpdateDTO);
        log.info(json);

        //expected(when & then)
        mockMvc.perform(patch("/account/{accountId}", account.getId() )
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("AccountController.updateAccount")
    void deleteAccountTest() throws Exception {
        //given
        Account account = createTestAccount();
        repository.save(account);

        //expected(when & then)
        mockMvc.perform(delete("/account/{accountId}", account.getId() )
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}