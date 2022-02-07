package com.jpa.jpashop;

import com.jpa.jpashop.domain.Delivery;
import com.jpa.jpashop.domain.Order;
import com.jpa.jpashop.domain.OrderItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class JpaShopApplication {
	public static void main(String[] args) {
		//DB 당 하나만 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		//스레드 간에 공유 금지
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Order order = new Order();
			OrderItem orderItem1 = new OrderItem();
			OrderItem orderItem2 = new OrderItem();
			Delivery delivery = new Delivery();

			order.setDelivery(delivery);
			order.addOrderItems(orderItem1);
			order.addOrderItems(orderItem2);

			em.persist(order);
			tx.commit();
		}catch (Exception e){
			tx.rollback();
		}finally {
			//사용이 끝나면 반드시 닫아야 한다.
			em.close();
		}
		emf.close();
	}

}
