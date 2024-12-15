package org.example;

import entity.Product;
import entity.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager= Persistence.createEntityManagerFactory("test").createEntityManager();


        entityManager.getTransaction().begin();

        Sale sale = new Sale();
        sale.setDateTime(LocalDateTime.now());

        Product product = new Product();
        product.setName("TestProduct1345");
//        product.setPrice(BigDecimal.TEN);
        product.setPrice(new BigDecimal(15));
        product.setQuantity(5);
        //Нашият продукт няма никакъв достъп до нашите продажби
        //Как можем да имаме достъп -> в класа Product си правя private Set<Sales> sales
        sale.setProduct(product);

        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }
}