package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.RestaurantDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Restaurant;
import edu.neu.csye6220.model.User;


@Service("RestaurantService")
public class RestaurantService {

	private RestaurantDAO restaurantDao;
	
	@Autowired
	public RestaurantService(RestaurantDAO restaurantDao)
	{
		this.restaurantDao=restaurantDao;
	}
	
	public void saveRestaurant(Restaurant restaurant) {
		 
		try {
			restaurantDao.create(restaurant);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Restaurant getRestaurant(User user)
	{
		try {
			Restaurant restaurant =new Restaurant();
			restaurant=restaurantDao.getByUserObj(user);
			return restaurant;
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
