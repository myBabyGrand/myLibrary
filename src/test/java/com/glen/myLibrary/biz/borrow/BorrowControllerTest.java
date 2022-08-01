package com.glen.myLibrary.biz.borrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
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
                .andExpect(content().string(""))
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
}