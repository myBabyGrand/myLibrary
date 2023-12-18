package com.glen.myLibrary.biz.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO {

    @Getter
    public class BookCreateDTO{
        private String title;

        private String author;

        private String description;

        private String publisher;

        @Enumerated(EnumType.STRING)
        private Category category;

        private String imageUrl;

        private String originUrl;

        private String isbn10;

        private String isbn13;

        private LocalDateTime publishedAt;

        @Builder
        public BookCreateDTO(String title, String author, String description, String publisher, Category category, String imageUrl, String originUrl, String isbn10, String isbn13, LocalDateTime publishedAt) {
            this.title = title;
            this.author = author;
            this.description = description;
            this.publisher = publisher;
            this.category = category;
            this.imageUrl = imageUrl;
            this.originUrl = originUrl;
            this.isbn10 = isbn10;
            this.isbn13 = isbn13;
            this.publishedAt = publishedAt;
        }
        public Book toEntity (){
            return Book.builder()
                    .title(this.title)
                    .author(this.author)
                    .subject(this.category)
                    .publishedAt(this.publishedAt)
                    .publisher(this.publisher)
                    .imageUrl(this.imageUrl)
                    .originUrl(this.originUrl)
                    .isbn13(this.isbn13)
                    .isbn10(this.isbn10)
                    .description(this.description)
                    .build();
        }
    }

    @Getter
    @Setter
    public class BookUpdateDTO{

        @NotBlank
        private Long bookId;
        private String description;
        private String imageUrl;
        private String originUrl;

        @Builder
        public BookUpdateDTO(Long bookId, String description, String imageUrl, String originUrl) {
            this.bookId = bookId;
            this.description = description;
            this.imageUrl = imageUrl;
            this.originUrl = originUrl;
        }
    }

}
