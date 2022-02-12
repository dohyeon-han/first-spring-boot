package com.jpa.jpa2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id @GeneratedValue
    private Long id;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Member member;
}
