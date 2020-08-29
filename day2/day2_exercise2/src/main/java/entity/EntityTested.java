/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonas
 */
public class EntityTested {
    
    public static void main(String[] args) {

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Jens", "Hansen", new Date());
        Customer c2 = new Customer("Per", "Andersen", new Date());
        
        em.getTransaction().begin();
        
        em.persist(c1);
        em.persist(c2);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
