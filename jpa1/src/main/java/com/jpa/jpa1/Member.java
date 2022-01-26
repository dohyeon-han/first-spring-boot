package com.jpa.jpa1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    @Id
    private long id;
    private String name;
}
