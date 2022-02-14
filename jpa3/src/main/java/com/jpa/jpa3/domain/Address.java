package com.jpa.jpa3.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
}
