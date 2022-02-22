package com.jpa.jpa3.domain.springDataRepository;

import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {
    List<Order> search(OrderSearch orderSearch);
}
