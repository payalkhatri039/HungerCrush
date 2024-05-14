package edu.neu.csye6220.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="menu")
public class FoodItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="food_id")
	private int foodId;
	
	@NotBlank
    @Size(min = 3, max = 50, message = "Please enter food name of between size 3-50")
	private String foodName;
	
	@NotBlank
    @Size(min = 3, max = 50, message = "Please enter food description of between size 3-50")
	private String description;
	
	@Min(value=0, message="Please enter minimum price as 0.0$")
	@Max(value=1000, message="Please enter maxinum price as 1000$")
	private double price;
	
	@OneToMany(mappedBy = "fooditem",cascade=CascadeType.ALL, orphanRemoval=true)
    private List<CartLine> cartline = new ArrayList<>();
	
//	@OneToMany(mappedBy = "fooditem")
//    private List<OrderItem> orderitem = new ArrayList<>();
	
	public FoodItem() {
		super();
	}


	public FoodItem(int foodId, String foodName, String description, double price) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.description = description;
		this.price = price;
	}


	public FoodItem(String foodName, String description, double price) {
		super();
		this.foodName = foodName;
		this.description = description;
		this.price = price;
	}
	
	public int getFoodId() {
		return foodId;
	}


	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "FoodItem [foodId=" + foodId + ", foodName=" + foodName + ", description=" + description + ", price="
				+ price + "]";
	}
	
	
	
}
