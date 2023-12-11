package com.glen.myLibrary.biz.book;

import com.glen.myLibrary.common.entity.SaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookController {

    private BookService bookService;

    //get 단건

    //get by search (paging 처리)

    //create
    @PostMapping("/book/save")
    public SaveResponse createBook(@RequestBody @Valid BookDTO.BookCreateDTO bookCreateDTO){
        return bookService.createBook(bookCreateDTO);
    }
    //update

    //delete
}
