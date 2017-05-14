package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@PrimaryKeyJoinColumn(name="personID")
@Component
@Scope("session")
@Entity
@Table(name="Customertable", uniqueConstraints={
        @UniqueConstraint(columnNames = "cell"),
        @UniqueConstraint(columnNames = "name")})
public class Customer extends Person{
	@Column(name="name" , unique = true, nullable = false)
	private String name;
	
	@Column(name="password")
    private String password;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="customer", cascade=CascadeType.ALL)
    private Email email;
	
	@Column(name="cell" , unique = true, nullable = false)
	private String cell;
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String address;
    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



	public Email getEmail() {
		return email;
		
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CustProd", joinColumns = { 
            @JoinColumn(name = "personID", nullable = false, updatable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "productId", 
                    nullable = false, updatable = false) })
    private Set<Product> prodlist = new HashSet<Product>();
	public Set<Product> getProdlist() {
		return prodlist;
	}

	public void setProdlist(Set<Product> prodlist) {
		this.prodlist = prodlist;
	}
	
    @OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	private Set <CustomerCart> customerCart;

	public Set<CustomerCart> getCustomerCart() {
		return customerCart;
	}

	public void setCustomerCart(Set<CustomerCart> customerCart) {
		this.customerCart = customerCart;
	}

}