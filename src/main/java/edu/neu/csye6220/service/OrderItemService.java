package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.OrderItemDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.OrderItem;

@Service("OrderItemService")
public class OrderItemService {
	
	private OrderItemDAO orderItemDao;
	
	@Autowired
	public OrderItemService(OrderItemDAO orderItemDao)
	{
		this.orderItemDao=orderItemDao;
	}
	
	public void saveOrderItem(OrderItem orderitem)
	{
		try {
			orderItemDao.create(orderitem);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OrderItem> getCustomerOrderItems(Customer customer)
	{
		try {
			return orderItemDao.get(customer);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public OrderItem getOrderItem(FoodItem fooditem, Customer customer)
	{
		try {
			OrderItem orderedfooditem=new OrderItem();
			orderedfooditem =orderItemDao.getOrderItem(fooditem, customer);
			System.out.println("found item in orderitem="+orderedfooditem); 
			if(orderedfooditem==null)
			{
				return null;
			}
			else
			{
				return orderedfooditem;
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
