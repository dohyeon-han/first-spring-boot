package com.jpa.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
    private String city;
    private String street;
}
