package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.CartLineDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.CartLine;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;

@Service("CustomerCartService")
public class CustomerCartService {
	
	private CartLineDAO cartLineDao;
	
	@Autowired
	public CustomerCartService(CartLineDAO cartLineDao)
	{
		this.cartLineDao=cartLineDao;
	}
	
	
	public void saveCartItem(CartLine cartline)
	{
		try {
			cartLineDao.create(cartline);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCartItem(CartLine cartline)
	{
		try {
			cartLineDao.delete(cartline);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCartItem(CartLine cartline)
	{
		try {
			cartLineDao.update(cartline);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<CartLine> getCustomerCartItems(Customer customer)
	{
		try {
			return cartLineDao.get(customer);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CartLine checkCartItem(FoodItem fooditem, Customer customer)
	{
		try {
			CartLine savedfooditem=new CartLine();
			savedfooditem =cartLineDao.getCartItem(fooditem, customer);
			System.out.println("found item in cartline="+savedfooditem); 
			if(savedfooditem==null)
			{
				return null;
			}
			else
			{
				return savedfooditem;
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
