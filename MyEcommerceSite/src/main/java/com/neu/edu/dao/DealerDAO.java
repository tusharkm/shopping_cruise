package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Dealer;
import com.neu.edu.pojo.Email;
import com.neu.edu.pojo.Person;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Customer;

public class DealerDAO extends DAO {

    public DealerDAO() {
    }

    public Dealer get(String name)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Dealer where name = :name");
            q.setString("name", name);
            Dealer dealer = (Dealer) q.uniqueResult();
            commit();
            return dealer;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + name, e);
        }
    }
    public Dealer getId(long personID)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Dealer where personID = :personID");
            q.setLong("personID", personID);
            Dealer dealer = (Dealer) q.uniqueResult();
            commit();
            return dealer;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get Dealer with id " + personID, e);
        }
    }
    public Dealer create(String username, String password,String email, String firstName, String lastName, String cell, String address,String company)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
          //  Email email=new Email(emailId);
            Dealer dealer=new Dealer(username,password);
      
            dealer.setFirstName(firstName);
            dealer.setLastName(lastName);
            dealer.setCell(cell);
            dealer.setAddress(address);
            dealer.setCompany(company);
            dealer.setEmail(email);
            dealer.setRoleRegistered("Dealer");
        
            
            getSession().save(dealer);
            
            commit();
            return dealer;
        } catch (HibernateException e) {
            rollback();
          
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public Dealer search(String name, String password)
            throws AdException {
        try {
            
        	begin();
            
            Query q = getSession().createQuery("from Dealer where name = :name and password = :password");
            
            q.setString("password", password);
            
            q.setString("name", name);
        
            Dealer dealer = (Dealer) q.uniqueResult();
          
            commit();
            
         /*   if(dealer != null){
            
            	return "Dealer";}
                
            else return "Invalid Dealer";*/
            
            return dealer;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + name, e);
        }
    }
    public void save(Dealer dealer) throws AdException {
        try {
            begin();
            getSession().update(dealer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the Dealer", e);
        }
    }
    
    public void delete(Dealer dealer)
            throws AdException {
        try {
            begin();
            getSession().delete(dealer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + dealer.getName(), e);
        }
    }
    
    
}