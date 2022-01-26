package com.jpa.jpa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

public class Jpa1Application {
	public static void main(String[] args) {
		//DB 당 하나만 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		//스레드 간에 공유 금지
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
//			추가
//			Member member = new Member();
//			member.setId(1L);
//			member.setName("helloA");
//			em.persist(member);

//			수정
			Member find = em.find(Member.class, 1L);
			find.setName("HELLOa");
			tx.commit();

//			JPQL
			List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
			for (Member m: result) {
				System.out.println("member = " + m.getName());
			}
		}catch (Exception e){
			tx.rollback();
		}finally {
			//사용이 끝나면 반드시 닫아야 한다.
			em.close();
		}
		emf.close();
	}

}
