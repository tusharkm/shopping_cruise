package com.neu.edu.pojo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;
	


@Entity
@Table(name="Product")
public class Product {

	@Id
    @GeneratedValue 
    @Column(name="productId", unique = true, nullable = false)
	private long productId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productDescription")
	private String productDescription;
	
	@Column(name="productImage")
	private String productImage;
	
	@Column(name="categoryId")
	private long categoryId;
	
	@Column(name="categoryName")
    private String categoryName;
	
/*	
    @Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="photo")
	   private MultipartFile photo;
	   
	*/
	   
/*	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}*/

	private float price;
    private int qty;
	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public long getProductId() {
		return productId;
	}
	
	
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	
	
	

	 public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	



	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}




@ManyToMany(fetch=FetchType.LAZY, mappedBy = "productset")
   private Set<Dealer> dealers = new HashSet<Dealer>();



	
	
	public Set<Dealer> getDealers() {
	return dealers;
}


public void setDealers(Set<Dealer> dealers) {
	this.dealers = dealers;
}

@ManyToMany(fetch=FetchType.LAZY, mappedBy = "prodlist")
private Set<Customer> customerlist = new HashSet<Customer>();



	public Set<Customer> getCustomerlist() {
	return customerlist;
}


public void setCustomerlist(Set<Customer> customerlist) {
	this.customerlist = customerlist;
}




	@ManyToOne
    @JoinColumn(name="categoryId", insertable = false, updatable = false)
    private Category category;
	
	public Product(){}
	//public Product( String productName, String productImage, String productDescription, long categoryId, String categoryName,MultipartFile photo)
	public Product( String productName, String productImage, String productDescription, long categoryId, String categoryName, Float price,int qty){
		this.productImage = productImage;
		//this.photo=photo;
		this.productName = productName;
		this.productDescription = productDescription;
		this.categoryName = categoryName;
		//this.categoryId = category.getCategoryId();
		this.categoryId = categoryId;
		this.price=price;
		this.qty=qty;
	}
	@ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
		private CustomerCart customerCart;
	
	    public CustomerCart getCustomerCart() {
		return customerCart;
	}


	
		public void setCustomerCart(CustomerCart customerCart) {
		this.customerCart = customerCart;
	}
	
	
}
