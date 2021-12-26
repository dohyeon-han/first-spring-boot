package com.inflearn.order.discount;

import com.inflearn.order.member.Grade;
import com.inflearn.order.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private final int rateDiscount = 10;//10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP)
            return price*rateDiscount/100;
        return 0;
    }
}
