package com.jpa.jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
public class Movie extends Item{
    private String director;
    private String actor;
}
