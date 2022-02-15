package com.jpa.jpa3.repository;

import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderSearch;
import com.jpa.jpa3.domain.QOrder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder qOrder = QOrder.order;
        JPAQuery<Order> orderJPAQuery = query.selectFrom(qOrder);

        if (orderSearch.getStatus() != null) {
            orderJPAQuery.where(qOrder.status.eq(orderSearch.getStatus()));
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            orderJPAQuery.join(qOrder.member)
                    .where(qOrder.member.name.contains(orderSearch.getMemberName()));
        }
        return orderJPAQuery.limit(1000).fetch();
    }
}
