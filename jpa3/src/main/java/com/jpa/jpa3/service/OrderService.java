package com.jpa.jpa3.service;

import com.jpa.jpa3.domain.*;
import com.jpa.jpa3.domain.item.Item;
import com.jpa.jpa3.repository.ItemRepository;
import com.jpa.jpa3.repository.MemberRepository;
import com.jpa.jpa3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    // 주문하기
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();

        OrderItem orderItem = OrderItem.builder()
                .item(item).orderPrice(item.getPrice()).count(count)
                .build();

        Order order = Order.builder()
                .member(member).delivery(delivery).orderItems(new OrderItem[]{orderItem})
                .build();

        orderRepository.save(order);
        return order.getId();
    }

    // 주문 취소
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
