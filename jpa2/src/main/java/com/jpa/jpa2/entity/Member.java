package com.jpa.jpa2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Team team;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void addOrder(Order order){
        if(orders == null) orders = new ArrayList<>();
        order.setMember(this);
    }
}
