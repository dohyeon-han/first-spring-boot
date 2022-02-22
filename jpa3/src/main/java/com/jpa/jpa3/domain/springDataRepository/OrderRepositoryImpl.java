package com.jpa.jpa3.domain.springDataRepository;

import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderSearch;
import com.jpa.jpa3.domain.QMember;
import com.jpa.jpa3.domain.QOrder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport
        implements CustomOrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {
        QOrder qOrder = QOrder.order;
        QMember qMember = QMember.member;
        JPAQuery<Order> orderJPAQuery = (JPAQuery<Order>) from(qOrder);

        if (orderSearch.getStatus() != null) {
            orderJPAQuery.where(qOrder.status.eq(orderSearch.getStatus()));
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            orderJPAQuery.leftJoin(qOrder.member, qMember)
                    .where(qMember.name.contains(orderSearch.getMemberName()));
        }
        return orderJPAQuery.limit(1000).fetch();
    }
}
