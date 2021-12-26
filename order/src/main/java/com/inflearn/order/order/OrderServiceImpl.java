package com.inflearn.order.order;

import com.inflearn.order.discount.DiscountPolicy;
import com.inflearn.order.member.Member;
import com.inflearn.order.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements  OrderService{
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private MemberRepository memberRepository = new MemoryMemberRepository();

    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
