package com.inflearn.order.discount;

import com.inflearn.order.member.Grade;
import com.inflearn.order.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private final int fixDiscount = 1000;//1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP)
            return fixDiscount;
        return 0;
    }
}
