package com.glen.myLibrary.biz.book;

import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Book extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

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
    public Book(Long id, String title, String author, String description, String publisher, Category category, String imageUrl, String originUrl, String isbn10, String isbn13, LocalDateTime publishedAt) {
        this.id = id;
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

    public void from (BookDTO.BookUpdateDTO bookUpdateDTO){
        this.id = bookUpdateDTO.getBookId();
        this.description = bookUpdateDTO.getDescription() != null ? bookUpdateDTO.getDescription() : this.description;
        this.imageUrl = bookUpdateDTO.getImageUrl() != null ? bookUpdateDTO.getImageUrl() : this.imageUrl;
        this.originUrl = bookUpdateDTO.getOriginUrl() != null ? bookUpdateDTO.getOriginUrl() : this.originUrl;
    }
}
