package com.neu.edu.pojo;

import javax.persistence.UniqueConstraint;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name="dealertable", uniqueConstraints={
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "name")
})

@PrimaryKeyJoinColumn(name="personID")
//@Component
//@Scope("session")
public class Dealer extends Person {

	@Column(name="name" , unique = true, nullable = false)
    private String name;
	
	@Column(name="password")
    private String password;
	
	//@OneToOne(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	@Column(name="email" , unique = true, nullable = false)
	private String email;
	

    public Dealer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	Dealer() {
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

	private String cell;
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	private String Address;
	
	private String Company;


	public String getCompany() {
		return Company;
	}

	public Set<Product> getProductset() {
		return productset;
	}

	public void setProductset(Set<Product> productset) {
		this.productset = productset;
	}

	public void setCompany(String company) {
		Company = company;
	}

@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(name = "DealerProd", joinColumns = { 
                @JoinColumn(name = "personID", nullable = false, updatable = false) }, 
                inverseJoinColumns = { @JoinColumn(name = "productId", 
                        nullable = false, updatable = false) })
        private Set<Product> productset = new HashSet<Product>();
}
