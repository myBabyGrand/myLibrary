package com.glen.myLibrary.biz.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Account account = Account.builder()
                .nickname("test닉네임")
                .email("testEmail@Email.com")
                .accountType(AccountType.USER)
                .password("testPassword")
                .joinedAt(LocalDateTime.now())
                .description("test Description")
                .build();
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

        //expected(when & then)
        mockMvc.perform(get("/accounts")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2L))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$.[0].nickname").value("test닉네임1"))
                .andExpect(jsonPath("$.[1].nickname").value("test닉네임2"))
                .andDo(print());
    }

}