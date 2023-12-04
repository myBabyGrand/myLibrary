package com.glen.myLibrary.biz.library;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Library extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private LibraryStatus libraryStatus = LibraryStatus.READY;

    private String introduce;

    private String description;

    private LocalDateTime approvedAt;

    @OneToOne(mappedBy = "library")
    @JsonManagedReference
    private LibraryPolicy libraryPolicy;

    @Builder
    public Library(Long id, String name, LibraryStatus libraryStatus, String introduce, String description, LocalDateTime approvedAt, LibraryPolicy libraryPolicy) {
        this.id = id;
        this.name = name;
        this.libraryStatus = libraryStatus;
        this.introduce = introduce;
        this.description = description;
        this.approvedAt = approvedAt;
        this.libraryPolicy = libraryPolicy;
    }
}
