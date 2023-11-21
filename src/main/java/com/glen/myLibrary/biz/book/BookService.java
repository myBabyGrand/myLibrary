package com.glen.myLibrary.biz.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book makeTestBook(){
        Book book = Book
                .builder()
                .title("책 제목")
                .author("김작가")
                .imageUrl("http://image.url")
                .description("책 설명")
                .isbn10("isbn10값")
                .isbn13("isbn13값")
                .originUrl("http://출처주소")
                .publisher("출판사")
                .publishedAt(LocalDateTime.now())
                .subject(Subject.NOVEL)
                .build();
        return bookRepository.save(book);
    }
}
