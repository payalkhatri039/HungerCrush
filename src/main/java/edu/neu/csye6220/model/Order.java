package edu.neu.csye6220.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int orderId;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")
	 private Customer customer;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderItem> orderitems = new ArrayList<>();
	 
	private Date date;
	
	private double totalCost;

	
	public Order() {
		super();
	}

	

	public Order(Customer customer, List<OrderItem> orderitems, Date date, double totalCost) {
		super();
		this.customer = customer;
		this.orderitems = orderitems;
		this.date = date;
		this.totalCost = totalCost;
	}



	public List<OrderItem> getOrderitems() {
		return orderitems;
	}



	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderitems=" + orderitems + ", date=" + date
				+ ", totalCost=" + totalCost + "]";
	}
	
	
	
}
