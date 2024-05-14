package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.CartLine;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.OrderItem;

@Component
public class OrderItemDAO extends DAO{

	public OrderItemDAO() {
		
	}
	
	 public OrderItem create(OrderItem orderitem) throws UserException {
	        try {
	            //save user object in the database
	        	begin();
	        	getSession().persist(orderitem);
	        	commit();
	        	close();
	        	
	        	return orderitem;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while creating orderitem: " + e.getMessage());
	        }
	    }
	 
	 public OrderItem getOrderItem(FoodItem fooditem, Customer customer)  throws UserException{
		 try {
		             
		          	begin();
		          	Query q = getSession().createQuery("from OrderItem where customer=:customer AND fooditem=:fooditem");
		          	//q.setString("username", username);
		          	q.setParameter("customer", customer);
		          	q.setParameter("fooditem", fooditem);
		          	OrderItem orderitem=(OrderItem)q.uniqueResult();
		          	//System.out.println("get="+user);
		          	commit();
		         	close();
		          	if(orderitem!=null)
		          	{
		          	return orderitem;
		          	}
		          	else
		          	{
		          		return null;
		          	}
		          	
		          } catch (HibernateException e) {
		              rollback();
		              throw new UserException("Could not get orderitem for " + fooditem, e);
		          }
		     }
	 
	 public List<OrderItem> get(Customer customer) throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from OrderItem where customer=:customer");
         	//q.setString("username", username);
         	q.setParameter("customer", customer);
         	List<OrderItem> orderitems=(List<OrderItem>)q.list();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	return orderitems;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get orderitem for " + customer, e);
         }
    	
    }
}
