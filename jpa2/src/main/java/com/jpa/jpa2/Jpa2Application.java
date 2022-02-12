package com.jpa.jpa2;

import com.jpa.jpa2.entity.Member;
import com.jpa.jpa2.entity.Team;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

//@SpringBootApplication
public class Jpa2Application {

    public static void main(String[] args) {
//		SpringApplication.run(Jpa2Application.class, args);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member user1 = Member.builder().username("user1").age(20).build();
            Member user2 = Member.builder().username("user2").age(20).build();
            Member user3 = Member.builder().username("user3").age(30).build();
            Member user4 = Member.builder().username("user4").age(20).build();
            Member user5 = Member.builder().username("user5").age(20).build();
            Member user6 = Member.builder().username("user6").age(30).build();

            Team team1 = Team.builder().teamName("A").build();
            Team team2 = Team.builder().teamName("B").build();

            team1.addMember(user1);
            team1.addMember(user2);
            team1.addMember(user3);
            team2.addMember(user4);
            team2.addMember(user5);
            team2.addMember(user6);

            em.persist(team1);
            em.persist(team2);

            em.flush();
            em.clear();

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
        emf.close();
    }

}
