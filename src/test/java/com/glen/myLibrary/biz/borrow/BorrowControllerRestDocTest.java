package com.glen.myLibrary.biz.borrow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.account.AccountService;
import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.book.BookService;
import com.glen.myLibrary.biz.library.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.mylibrary.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class BorrowControllerRestDocTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryBookService libraryBookService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LibraryMemberService libraryMemberService;

//    @BeforeEach
//    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//    }

    @Test
    @DisplayName("sampleGetTest")
    void sample_getTest () throws Exception{
        this.mockMvc.perform(get("/borrow/{borrowId}", 1L).accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("borrow-get"
                        , pathParameters(
                                parameterWithName("borrowId").description("대출ID")
                        ),responseFields(
                                fieldWithPath("id").description("대출ID")
                                ,fieldWithPath("startAt").description("대출시작일시")
                                ,fieldWithPath("expireAt").description("대출종료일시")
                                ,fieldWithPath("returnedAt").description("반납일시")
                                ,fieldWithPath("extendTimes").description("연장횟수")
                                ,fieldWithPath("book").description("대출책")
                                ,fieldWithPath("borrower").description("빌린사람")
                                ,fieldWithPath("lender").description("빌려준도서관")
                        )
                ));
    }

    @Test
    @DisplayName("samplePostTest")
    void sample_postTest () throws Exception{
        Library library = libraryService.makeTestLibrary();
        Book book = bookService.makeTestBook();
        LibraryBook libraryBook = libraryBookService.makeTestLibraryBook(book, library);
        Account account = accountService.makeTestAccount();
        LibraryMember libraryMember = libraryMemberService.makeTestLibraryMember(account, library);

        BorrowCreateDTO borrowCreateDTO = BorrowCreateDTO
                .builder()
                .libraryBookId(libraryBook.getId())
                .libraryMemberId(libraryMember.getId())
                .libraryId(library.getId())
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build();

        String json = objectMapper.writeValueAsString(borrowCreateDTO);

        this.mockMvc.perform(post("/borrow")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("borrow-post"
                        , requestFields(
                                fieldWithPath("libraryId").description("도서관ID")
                                ,fieldWithPath("libraryMemberId").description("회원ID")
                                ,fieldWithPath("libraryBookId").description("도서관책ID")
                                ,fieldWithPath("expiredAt").description("대출종료일시")
                        )
                ));
    }

}
