package com.jpa.jpa2;

import com.jpa.jpa2.entity.Member;
import com.jpa.jpa2.entity.QMember;
import com.jpa.jpa2.entity.QOrder;
import com.jpa.jpa2.entity.QTeam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class QueryDsl {

    @PersistenceContext
    EntityManager em;

    public void queryDSL() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;
        QOrder order = QOrder.order;
        QTeam team = QTeam.team;

        List<Member> members = query.selectFrom(member)
                .where(member.username.like("%user%"))
                .orderBy(member.age.desc())
                .offset(3).limit(10)
                .fetchAll()
                .fetch();
        System.out.println(members.size());
        for (Member m : members) {
            System.out.println("m = " + m.toString());
            System.out.println("team = " + m.getTeam().getTeamName());
        }

        List<Tuple> fetch = query.select(member.username, member.team.teamName)
                .from(member)
                .join(member.team, team)
                .fetch();
        for (Tuple t : fetch) {
            System.out.println("member " + t.get(member.username) + " : " + t.get(member.team.teamName));
        }

        List<MemberDto> fetch1 = query.select(Projections.constructor(MemberDto.class
                        , member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto m : fetch1) {
            System.out.println("m = " + m.getUsername() + ", " + m.getAge());
        }

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, member);
        jpaUpdateClause.where(member.age.goe(30))
                .set(member.age, member.age.add(20))
                .execute();
        JPADeleteClause jpaDeleteClause = new JPADeleteClause(em, member);
        jpaDeleteClause.where(member.age.goe(30)).execute();

        List<Member> fetch2 = query.selectFrom(member).fetch();
        for (Member m : fetch2) {
            System.out.println(m.toString());
        }

        BooleanBuilder builder = new BooleanBuilder();
        if(fetch2.size()!=0){
            builder.and(member.age.goe(10));
            if(fetch2.get(0).getUsername().contains("user"))
                builder.and(member.username.like("%user%"));
        }
        List<Member> fetch3 = query.selectFrom(member)
                .where(builder)
                .fetch();
        for (Member m : fetch3){
            System.out.println(m.toString());
        }
    }
}
