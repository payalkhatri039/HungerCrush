package edu.neu.csye6220.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orderItems")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int orderItemId;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customerId")
	 private Customer customer;
	
//	@ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "food_id")
//	private FoodItem fooditem;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "orderId")
	 private Order order;
	
	private String foodName;
	
	private double cost;
	
	private int quantity;

	public OrderItem() {
		super();
	}

	public OrderItem(Order order, Customer customer, String foodName, double cost, int quantity) {
		super();
		this.order=order;
		this.customer=customer;
		//this.order = order;
		//this.fooditem = fooditem;
		this.foodName=foodName;
		this.cost = cost;
		this.quantity = quantity;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

//	public FoodItem getFooditem() {
//		return fooditem;
//	}
//
//	public void setFooditem(FoodItem fooditem) {
//		this.fooditem = fooditem;
//	}
	

	public double getCost() {
		return cost;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", cost="
				+ cost + ", quantity=" + quantity + "]";
	}
	
	
}
