package com.glen.myLibrary.biz.book;

import com.glen.myLibrary.common.entity.SaveResponse;
import com.glen.myLibrary.common.exception.DataNotFoundException;
import com.glen.myLibrary.common.exception.MyLibraryBizException;
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
                .category(Category.NOVEL)
                .build();
        return bookRepository.save(book);
    }

    public SaveResponse createBook(BookDTO.BookCreateDTO bookCreateDTO){
        //TODO isbn13이나 isbn10으로 조회해서 있는 건이면 이미 등록된 데이터
        if(isExistWithIsbn(bookCreateDTO.getIsbn10(), bookCreateDTO.getIsbn13())){
           throw new MyLibraryBizException("이미존재하는 데이터 입니다");
        }

        Book book = bookRepository.save(bookCreateDTO.toEntity());
        return SaveResponse.builder()
                .key(book.getId())
                .processCount(1).build();

    }

    private boolean isExistWithIsbn(String isbn10, String isbn13) {
        if(!isbn10.isEmpty() && bookRepository.findByIsbn10(isbn10).isPresent()){
            return true;
        }
        if(!isbn13.isEmpty() && bookRepository.findByIsbn13(isbn13).isPresent()){
            return true;
        }
        return false;
    }

    public SaveResponse updateBook(BookDTO.BookUpdateDTO bookUpdateDTO) {
        Book book = bookRepository
                .findById(bookUpdateDTO.getBookId())
                .orElseThrow(DataNotFoundException::new);
        book.from(bookUpdateDTO);
        bookRepository.save(book);

        return SaveResponse.builder()
                .key(book.getId())
                .processCount(1).build();
    }

    public SaveResponse deleteBook(Long bookId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(DataNotFoundException::new);
        bookRepository.deleteById(bookId);
        return SaveResponse.builder()
                .key(book.getId())
                .processCount(1).build();

    }
}
