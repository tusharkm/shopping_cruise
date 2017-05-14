package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;





@Entity
@Table(name="Category")

public class Category {

	@Id
    @GeneratedValue
    @Column(name="categoryId", unique = true, nullable = false)
    private long categoryId;
	
	@Column(name="categoryName")
	private String categoryName;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="category")
	private Set<Product> product= new HashSet<Product>();
	
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	public void addProduct(Product product) {
        getProduct().add(product);
    }
	
	
	public Category(String categoryName) {
        this.categoryName = categoryName;
        this.product = new HashSet<Product>();
    }

    Category() {
    }
	
}
