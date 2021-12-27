package com.inflearn.order.order;

import com.inflearn.order.discount.DiscountPolicy;
import com.inflearn.order.member.Member;
import com.inflearn.order.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Component("service")
@Service
public class OrderServiceImpl implements  OrderService{
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private MemberRepository memberRepository = new MemoryMemberRepository();
    @Autowired
    @Qualifier("fix")
    private DiscountPolicy discountPolicy;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
