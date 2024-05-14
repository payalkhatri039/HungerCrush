package edu.neu.csye6220.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="hungercrush")
public class Restaurant{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="restaurant_id")
	private int restaurantID;
	private String about;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="fk_user_id", referencedColumnName="user_id")
	private User user;
	
	public Restaurant() {
		super();
	}
	public Restaurant(int restaurantID, String about) {
		super();
		this.restaurantID = restaurantID;
		this.about = about;
	}
	
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantID=" + restaurantID + ", about=" + about + "]";
	}
	
	
	
}
