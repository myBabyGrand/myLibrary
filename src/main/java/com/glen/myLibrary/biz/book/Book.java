package com.glen.myLibrary.biz.book;

import com.glen.myLibrary.biz.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String author;

    private String description;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    private String imageUrl;

    private String originUrl;

    private String isbn10;

    private String isbn13;

    private LocalDateTime publishedAt;

}
