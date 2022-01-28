package com.jpa.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    private String city;

    private String street;

    private String zipcode;

    private DeliveryStatus status;


}
