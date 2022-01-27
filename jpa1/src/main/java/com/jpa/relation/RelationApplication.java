package com.jpa.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RelationApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            // 회원 저장
            Member member1 = new Member();
            member1.setName("member1");
            //member.setTeamId(team.getId());
            member1.setTeam(team);
            team.getMembers().add(member1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member1");
            //member.setTeamId(team.getId());
            member2.setTeam(team);
            team.getMembers().add(member1);
            em.persist(member2);

            em.flush();  // 1차 캐시에 있는 데이터 보내기(find 시 DB에서 조회)
            em.clear();

            //조회
            Member findMember = em.find(Member.class, member1.getId());
            //멤버가 속한 팀을 다시 조회 -> 객체지향적X
            //Team findTeam = em.find(Team.class, findMember.getTeamId());
            //System.out.println("findTeam = " + findTeam.getName());

            //객체지향적
            System.out.println("findTeam = " + findMember.getTeam().getName());

            System.out.println(team.getMembers().size());

            //수정
            //findTeam.setName("TeamB");

            tx.commit(); // [트랜잭션] 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();

        }
        emf.close();
    }
}
