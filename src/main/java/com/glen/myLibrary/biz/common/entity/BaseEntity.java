package com.glen.myLibrary.biz.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {
    @Column(updatable= false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(updatable= false)
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String LastModifiedBy;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        lastModifiedDate = now;
        createdBy = "createDefault";//FIXME : appId나 사용자ID로 변경필요
        LastModifiedBy = "LastModifiedByDefault";//FIXME : appId나 사용자ID로 변경필요
    }

    @PreUpdate
    public void preUpdate(){
        lastModifiedDate = LocalDateTime.now();
        LastModifiedBy = "LastModifiedByDefault";//FIXME : appId나 사용자ID로 변경필요
    }
}
