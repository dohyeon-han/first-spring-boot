package com.jpa.jpa3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
