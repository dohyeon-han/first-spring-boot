package com.jpa.jpa3.service;

import com.jpa.jpa3.domain.Address;
import com.jpa.jpa3.domain.Member;
import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderStatus;
import com.jpa.jpa3.domain.item.Book;
import com.jpa.jpa3.domain.item.Item;
import com.jpa.jpa3.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService service;

    @Autowired
    OrderRepository repository;

    @Test
    public void 상품주문() {
        Item item = createBook();
        Member member = createMember();
        int orderCount = 2;

        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        Order order = repository.findById(orderId);

        assertEquals(OrderStatus.ORDER, order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(10000 * 2, order.getTotalPrice());
        assertEquals(8, item.getQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //Given
        Member member = createMember();
        Item item = createBook();
        int orderCount = 11; //재고 보다 많은 수량

        //When
        assertThrows(IllegalArgumentException.class,
                ()->service.order(member.getId(), item.getId(), orderCount));
    }


    @Test
    public void 주문취소() {
        //Given
        Member member = createMember();
        Item item = createBook(); //이름, 가격, 재고
        int orderCount = 2;

        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        //When
        service.cancelOrder(orderId);

        //Then
        Order getOrder = repository.findById(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getQuantity());
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("user1")
                .address(Address.builder().city("서울").city("강가").build())
                .build();
        em.persist(member);
        return member;
    }

    private Book createBook() {
        Book book = Book.builder().author("kim").name("JPA").quantity(10).price(10000).build();
        em.persist(book);
        return book;
    }
}