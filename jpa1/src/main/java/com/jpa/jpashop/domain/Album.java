package com.jpa.jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("album")
public class Album extends Item{
    private String artist;
    private String etc;
}
