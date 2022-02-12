package com.jpa.jpa2;

import com.jpa.jpa2.entity.Member;
import com.jpa.jpa2.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Configuration
@SpringBootApplication
public class Jpa2Application {
    public static void main(String[] args) {
        SpringApplication.run(Jpa2Application.class, args);
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Autowired
    QueryDsl queryDsl;

    @PersistenceUnit
    EntityManagerFactory emf;

    @PostConstruct
    public void init() {
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

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        queryDsl.queryDSL();
    }
}
