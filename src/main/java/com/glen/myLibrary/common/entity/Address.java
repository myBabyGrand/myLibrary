package com.glen.myLibrary.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    private String city;
    private String street;
    private String zipcode;
}