package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.entity.Address;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
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

    @OneToOne
    private Address address;

    private LocalDateTime approvedAt;

}
