package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.OrderDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.Order;

@Service("OrderService")
public class OrderService {

	private OrderDAO orderDao;
	
	@Autowired
	public OrderService(OrderDAO orderDao)
	{
		this.orderDao=orderDao;
	}
	
	public void saveOrder(Order order)
	{
		try {
			orderDao.create(order);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Order> getCustomerOrders(Customer customer)
	{
		try {
			return orderDao.get(customer);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateOrder(Order order)
	{
		try {
			orderDao.update(order);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Order> getAllOrders()
	{
		try {
			return orderDao.getAll();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

