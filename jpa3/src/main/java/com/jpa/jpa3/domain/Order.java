package com.jpa.jpa3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Member member, Delivery delivery, OrderItem...  orderItems) {
        this.setMember(member);
        this.setDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            this.addOrderItem(orderItem);
        }
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new RuntimeException("이미 배송 완료된 상품입니다.");
        }

        this.status = OrderStatus.CANCEL;
        for(OrderItem orderItem : this.orderItems){
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : this.orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
