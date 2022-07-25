package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.common.entity.Address;
import com.glen.myLibrary.biz.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Library extends BaseEntity {

    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private LibraryStatus libraryStatus = LibraryStatus.READY;

    private String introduce;

    private String description;

    private Address address;

    private LocalDateTime approvedAt;

}
