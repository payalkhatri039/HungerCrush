package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.Order;

@Component
public class OrderDAO extends DAO{
	
	public OrderDAO() {
		
	}
	public Order create(Order order) throws UserException {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(order);
        	commit();
        	close();
        	
        	return order;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating order: " + e.getMessage());
        }
    }
	
	public List<Order> get(Customer customer) throws UserException {
   	 try {
           
        	begin();
        	Query q = getSession().createQuery("from Order where customer=:customer");
        	//q.setString("username", username);
        	q.setParameter("customer", customer);
        	List<Order> orders=(List<Order>)q.list();
        	//System.out.println("get="+user);
        	commit();
         	close();
        	return orders;
        	
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not get order for " + customer, e);
        }
   	
   }
	
	 public void update(Order order) throws UserException {
	   	 try {
	            //save user object in the database
	        	begin();
	        	getSession().merge(order);
	        	commit();
	         	close();
	        	
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while updating order: " + e.getMessage());
	        }
	   }
	 
	 public List<Order> getAll() throws UserException {
		 try {
	           
	        	begin();
	        	Query q = getSession().createQuery("from Order");
	        	//q.setString("username", username);
	        	List<Order> orders=(List<Order>)q.list();
	        	//System.out.println("get="+user);
	        	commit();
	        	close();
	        	return orders;
	        	
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Could not get all customer orders " + e);
	        }
	 }
		
}
