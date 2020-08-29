/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonas
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
      public static void main(String[] args) {
        
        CustomerFacade cf = new CustomerFacade();
        
        int findCustomer = 1;
        System.out.println("Customer with ID no. " + findCustomer + " is: " + findByID(findCustomer));
        
        String lastName = "Hansen";
        System.out.println("Customer with last name " + lastName + " is:" + cf.findByLastName(lastName));
        
        System.out.println("The number of customers are: " + cf.getNumberOfCustomers());
        
        System.out.println("Here is a list of all customers: " + cf.allCustomers());
        
        cf.addCustomer("Sofie", "Jensen");

        System.out.println("Here is a list of all customers: " + cf.allCustomers());
        
    }
    
    public static Customer findByID(int id){
        
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Customer> query = em.createQuery("Select c from Customer c where customer.id=:id", Customer.class);
        Customer c = query.getSingleResult();
        em.close();
        return c;
    }   
    
    public List<Customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try{
        TypedQuery<Customer> query = em.createQuery("Select customer from Customer customer where customer.lastName = " + name, Customer.class);
        return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
        Query query = em.createQuery("SELECT COUNT(customer.ID) FROM Customer customer");
        return (int) query.getSingleResult();
        }finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
        Query query = em.createQuery("Select * from Customer customer");
        return query.getResultList();
        }finally {
            em.close();
        }
    }
        
    public Customer addCustomer(String fName, String lName){
        Customer customer = new Customer(fName, lName, new Date());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
}
