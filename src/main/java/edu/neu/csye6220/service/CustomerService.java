package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.CustomerDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.User;

@Service("CustomerService")
public class CustomerService {

	private CustomerDAO customerDao;
	
	@Autowired
	public CustomerService(CustomerDAO customerDao)
	{
		this.customerDao=customerDao;
	}
	
	public void saveCustomer(Customer customer) {
		 
		try {
			customerDao.create(customer);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Customer getCustomer(User user)
	{
		try {
			Customer customer =new Customer();
			customer=customerDao.getByUserObj(user);
			return customer;
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
