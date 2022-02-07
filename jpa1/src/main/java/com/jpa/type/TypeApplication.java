package com.jpa.type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypeApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = Member.builder()
                    .homeAddress(new Address("부산", "마린시티"))
                    .favoriteFoods(new HashSet<>())
                    .addressHistory(new ArrayList<>()).build();

            member.addFavoriteFoods("보쌈");
            member.addFavoriteFoods("피자");
            member.addFavoriteFoods("샌드위치");

            AddressEntity addressEntity = new AddressEntity("서울", "가로수 길");
            member.addAddressHistory(addressEntity);

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 1L);

            Address homeAddress = findMember.getHomeAddress();

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String food : favoriteFoods) {
                System.out.println("favorite food = " + food);
            }

            List<AddressEntity> addressHistory = findMember.getAddressHistory();
            System.out.println(addressHistory.get(0).toString());

            tx.commit(); // [트랜잭션] 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
