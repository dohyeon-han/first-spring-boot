package com.jpa.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private int orderPrice;

    private int count;
}
