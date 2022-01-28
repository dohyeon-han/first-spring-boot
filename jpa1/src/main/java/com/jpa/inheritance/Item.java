package com.jpa.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@Inheritance(strategy= InheritanceType.JOINED)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Entity
public class Item extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
