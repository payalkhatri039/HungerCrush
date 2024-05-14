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
@Table(name="cartLines")
public class CartLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int cartLineId;
	
	 @ManyToOne(cascade = CascadeType.REFRESH)
	 @JoinColumn(name = "customerId")
	 private Customer customer;
	
	@ManyToOne
    @JoinColumn(name = "food_id")
	private FoodItem fooditem;
	
	private int quantity;

	public CartLine() {
		super();
	}

	public CartLine(Customer customer, FoodItem fooditem, int quantity) {
		super();
		this.customer = customer;
		this.fooditem = fooditem;
		this.quantity = quantity;
	}

	public int getCartLineId() {
		return cartLineId;
	}

	public void setCartLineId(int cartLineId) {
		this.cartLineId = cartLineId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public FoodItem getFooditem() {
		return fooditem;
	}

	public void setFooditem(FoodItem fooditem) {
		this.fooditem = fooditem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartLine [cartLineId=" + cartLineId + ", customer=" + customer + ", fooditem=" + fooditem
				+ ", quantity=" + quantity + "]";
	}
	
	
	
	
}
