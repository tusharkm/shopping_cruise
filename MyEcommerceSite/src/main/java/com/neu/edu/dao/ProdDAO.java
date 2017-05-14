package com.neu.edu.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.Product;
import com.neu.edu.pojo.Dealer;


public class ProdDAO extends DAO {

    public Product create(String productName, String productImage,String productDescription, long categoryId, String categoryName,Float price, int qty)
            throws AdException {
        try {
            begin();
            Product product = new Product( productName,  productImage,  productDescription,  categoryId,  categoryName, price, qty);
            getSession().save(product);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create product", e);
            throw new AdException("Exception while creating product: " + e.getMessage());
        }
    }
    public List list() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Product");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the Product", e);
        }
    }
    public ArrayList<Product> get(String catName)
            throws AdException {
        try {
            begin();
           
            Query q = getSession().createQuery("from Product where categoryName = :catName");
            q.setString("catName", catName);
            ArrayList<Product> prodList= new ArrayList<Product>();
            prodList= (ArrayList<Product>) q.list();
            commit();
            return prodList;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get product " + catName, e);
        }
    }
    public Product getId(long productId)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Product where productId = :productId");
            q.setLong("productId", productId);
            Product product = (Product) q.uniqueResult();
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get Product with id " + productId, e);
        }
    }
    public void delete(Product product)
            throws AdException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete product", e);
        }
    }
        public void updateQty(Product product)
                throws AdException {
            try {
                begin();
                getSession().update(product);
                commit();
            } catch (HibernateException e) {
                rollback();
                throw new AdException("Could not update product", e);
            }
    }
    
 
}