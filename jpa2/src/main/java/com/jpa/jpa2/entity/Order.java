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
@Table(name = "Orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
