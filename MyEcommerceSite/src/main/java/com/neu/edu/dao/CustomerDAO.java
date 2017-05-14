package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Email;
import com.neu.edu.pojo.Person;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Dealer;

public class CustomerDAO extends DAO {

    public CustomerDAO() {
    }

    public Customer get(String name)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Customer where name = :name");
            q.setString("name", name);
            Customer customer = (Customer) q.uniqueResult();
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get Customer " + name, e);
        }
    }

    public void save(Customer customer) throws AdException {
        try {
            begin();
            getSession().update(customer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the Customer", e);
        }
    }
    public Customer create(String username, String password,String emailId, String firstName, String lastName, String cell, String address)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
            Email email=new Email(emailId);
            Customer customer=new Customer(username,password);
            
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setCell(cell);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setRoleRegistered("Customer");
            email.setCustomer(customer);
            
            getSession().save(customer);
            
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + user, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(Customer customer)
            throws AdException {
        try {
            begin();
            getSession().delete(customer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + customer.getName(), e);
        }
    }
    public Customer search(String name, String password)   // Search dealer
            throws AdException {
        try {
            
        	begin();
            
            Query q = getSession().createQuery("from Customer where name = :name and password = :password");
            q.setString("name", name);
            q.setString("password", password);
            q.setMaxResults(1);
        
                       
           // Customer user = (Customer) q.list();
            
            Customer customer = (Customer) q.uniqueResult();
        
            commit();
            
       /*     if(user != null){
            return "Customer";}
            else return "Invalid Customer";*/
            
            return customer;
        } catch (HibernateException e) {
        	
            rollback();
            throw new AdException("Could not get user " + e.getMessage());
        }
    }
    public Customer getId(long personID)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Customer where personID = :personID");
            q.setLong("personID", personID);
            Customer customer = (Customer) q.uniqueResult();
            commit();
            return customer;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get Customer with id " + personID, e);
        }
    }
}