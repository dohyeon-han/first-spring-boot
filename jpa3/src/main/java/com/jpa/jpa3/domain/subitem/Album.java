package com.jpa.jpa3.domain.subitem;

import com.jpa.jpa3.domain.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}
