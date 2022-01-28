package com.jpa.inheritance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class InheritanceApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setAuthor("홍길동");
            book.setIsbn("12345");
            book.setName("홍길동전");
            book.setPrice(9900);
            em.persist(book);

            Album album = new Album();
            album.setArtist("한국");
            album.setName("아리랑");
            album.setPrice(10000);
            em.persist(album);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
