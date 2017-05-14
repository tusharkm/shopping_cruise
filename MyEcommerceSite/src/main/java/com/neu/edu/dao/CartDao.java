package com.neu.edu.dao;


import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Dealer;
import com.neu.edu.pojo.Email;
import com.neu.edu.pojo.Person;
import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.CustomerCart;

public class CartDao extends DAO {
	public CartDao() {
    }
	 //public CustomerCart create(int id,int cart_id,Customer customer,int qty,String State,float price)
	 public CustomerCart create(int qty,Product product,Customer customer, int cart_id)
			 throws AdException {
	        try {
	            begin();
	           
	            
	            		
	            CustomerCart customerCart= new CustomerCart(cart_id,customer,qty,"AddedToCart",product.getPrice(),product.getProductId(),product.getProductName());
	            getSession().save(customerCart);
	            commit();
	            return customerCart;		

	        } catch (HibernateException e) {
	            rollback();
	          
	            throw new AdException("Exception while creating Customercart: " + e.getMessage());
	        }
	    }

	 public  ArrayList<CustomerCart>  getCartItem(long customer)
			 throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from CustomerCart where customer = :customer and State=:State");
	            q.setLong("customer", customer);
	            q.setString("State", "AddedToCart");
	            ArrayList<CustomerCart> CustomerCartList= new ArrayList<CustomerCart>();
	            CustomerCartList= (ArrayList<CustomerCart>) q.list();
	            commit();
	            return CustomerCartList;
	            		

	        } catch (HibernateException e) {
	            rollback();
	          
	            throw new AdException("Exception while creating Customercart: " + e.getMessage());
	        }
	    }
	 
	 public  ArrayList<CustomerCart>  getCartItembyId(int cart_id)
			 throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from CustomerCart where cart_id = :cart_id");
	            q.setLong("cart_id", cart_id);
	            System.out.println("Incart"+cart_id );
	            ArrayList<CustomerCart> CustomerCartList= new ArrayList<CustomerCart>();
	            CustomerCartList= (ArrayList<CustomerCart>) q.list();
	            commit();
	            return CustomerCartList;
	            		

	        } catch (HibernateException e) {
	            rollback();
	          
	            throw new AdException("Exception while creating Customercart: " + e.getMessage());
	        }
	    }
	 
	 public void updateStatus(int id)
             throws AdException {
         try {
        	 ArrayList<CustomerCart>carts = getCartItembyId(id) ;
        	 for(CustomerCart customerCart :carts){
        		 begin();
        		 customerCart.setState("checkout");
        		 getSession().update(customerCart);
        		 commit();
        	 }
        	 
             
             
         } catch (HibernateException e) {
             rollback();
             throw new AdException("Could not delete product", e);
         }
	 
}}
