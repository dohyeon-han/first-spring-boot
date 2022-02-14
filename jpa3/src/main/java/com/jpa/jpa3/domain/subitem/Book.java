package com.jpa.jpa3.domain.subitem;

import com.jpa.jpa3.domain.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
}
