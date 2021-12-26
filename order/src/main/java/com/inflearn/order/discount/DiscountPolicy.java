package com.inflearn.order.discount;

import com.inflearn.order.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
