package com.glen.myLibrary.biz.borrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get("/getFirstBorrow"))
                .andExpect(status().isOk())
                .andExpect(content().string("firstBorrow"))
                .andDo(print());
    }


    @Test
    void postTest() throws Exception {
        mockMvc.perform(post("/postBorrow")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userId", "1234")
                        .param("bookId", "5678"))
                .andExpect(status().isOk())
                .andExpect(content().string("borrow OK"))
                .andDo(print());

        mockMvc.perform(post("/postBorrow2")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userId", "1234")
                        .param("bookId", "5678"))
                .andExpect(status().isOk())
                .andExpect(content().string("borrow OK"))
                .andDo(print());

        mockMvc.perform(post("/postBorrow3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\" : \"1234\", \"bookId\" : \"5678\"}"))
                .andExpect(status().isOk())
//                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    @DisplayName("필수값 체크")
    void postError_test() throws Exception {
        mockMvc.perform(post("/postBorrow3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\" : \"1234\", \"bookId\" : \"\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value("bookId는 null 일 수 없습니다."))
                .andDo(print());
    }
    @Test
    @DisplayName("필수값 체크 using ExceptionHandler")
    void postError_test_exceptionHandler() throws Exception {
        mockMvc.perform(post("/postBorrow4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\" : \"1234\", \"bookId\" : \"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다"))
                //TODO : 메시지 용어로 개선 필요
                .andExpect(jsonPath("$.validation.bookId").value("bookId는 null 일 수 없습니다."))
                .andDo(print());
    }
}