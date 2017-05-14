package com.neu.edu.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import com.neu.edu.dao.CartDao;


@Component
@Entity
@Table(name = "customerCart", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class CustomerCart {
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
public Set<Product> getProductid() {
		return productid;
	}
	
	public void setProductid(Set<Product> productid) {
		this.productid = productid;
	}
	public Customer getCustomerid() {
		return customer;
	}
	
	
	public void setCustomerid(Customer customer_id) {
		this.customer = customer_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Id
  	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int cart_id;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerCart")
	private Set <Product> productid;
	
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	@ManyToOne
    @JoinColumn(name="personID")
	private Customer customer;
	private int qty;
	private long product_id;
	private String prodName;
	private String State;
	private float price;
	
	CustomerCart()
		{}
	 public CustomerCart(int cart_id,Customer customer_id,int qty,String State,float price,long product_id,String ProdName)
	 {
		 
		 this.cart_id=cart_id;
		 this.customer=customer_id;
		 this.qty=qty;
		 this.State=State;
		 this.price=price;
		 this.product_id=product_id;
		 this.prodName=ProdName;
		 
		 
	 }
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		prodName = prodName;
	}
}
