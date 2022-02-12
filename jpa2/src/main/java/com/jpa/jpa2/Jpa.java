package com.jpa.jpa2;

import com.jpa.jpa2.entity.Member;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

//@Component
public class Jpa {

    @PersistenceUnit
    EntityManagerFactory emf;

    public void jpa() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Query query = em.createQuery("SELECT new com.jpa.jpa2.MemberDto(m.username, m.age) FROM Member AS m " +
                            "WHERE m.age = :age", MemberDto.class)
                    .setParameter("age", 20);
//            query.setFirstResult(10);
//            query.setMaxResults(20);
            List<MemberDto> members = query.getResultList();

            for (MemberDto dto : members) {
                System.out.println(dto.getUsername());
                System.out.println(dto.getAge());
            }

            List<Member> resultList = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class)
                    .getResultList();

            for (Member m : resultList) {
                System.out.println("username : " + m.getUsername());
                System.out.println("team_name : " + m.getTeam().getTeamName() + "\n");
            }

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> cq = cb.createQuery(Member.class);
            Root<Member> from = cq.from(Member.class);
            Predicate equal = cb.equal(from.get("username"), "user1");
            Order age = cb.desc(from.get("age"));
            cq.select(from).where(equal).orderBy(age);
            List<Member> resultList1 = em.createQuery(cq).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
