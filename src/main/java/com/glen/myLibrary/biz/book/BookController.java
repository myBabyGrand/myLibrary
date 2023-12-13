package com.glen.myLibrary.biz.book;

import com.glen.myLibrary.common.entity.SaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/book/{bookId}")
    public SaveResponse updateBook(@PathVariable Long bookId, @RequestBody @Valid BookDTO.BookUpdateDTO bookUpdateDTO){
        return bookService.updateBook(bookUpdateDTO);
    }

    //delete
    @DeleteMapping("book/{bookId}")
    public SaveResponse deleteBook(@PathVariable Long bookId){
        return bookService.deleteBook(bookId);
    }
}
