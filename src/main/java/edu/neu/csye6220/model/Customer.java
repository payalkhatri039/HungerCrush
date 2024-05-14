package edu.neu.csye6220.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column
	private int customerId;
	
	 //private FoodCart foodcart;
	
@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.EAGER)
@JoinColumn(name="fk_user_id", referencedColumnName="user_id")
	 private User user;

	@OneToMany(mappedBy = "customer")
	private List<CartLine> cartLines = new ArrayList<>();

	@OneToMany(mappedBy = "customer")
	private List<OrderItem> orderitem = new ArrayList<>();

	@OneToMany(mappedBy = "customer")
	private List<Order> orders = new ArrayList<>();
	 

	public Customer() {
		super();
	}
	

	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", user=" + user + "]";
	}

	
	
}
