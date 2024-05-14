package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.CartLine;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.User;

@Component
public class CartLineDAO extends DAO{
	
	public CartLineDAO() {
		
	}
	
	 public List<CartLine> get(Customer customer) throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from CartLine where customer=:customer");
         	//q.setString("username", username);
         	q.setParameter("customer", customer);
         	List<CartLine> cartlines=(List<CartLine>)q.list();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	return cartlines;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get cart line for " + customer, e);
         }
    	
    }
    public CartLine create(CartLine cartline) throws UserException {
          try {
            //save user object in the database
        	begin();
        	getSession().persist(cartline);
        	commit();
        	close();
        	return cartline;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating cart line: " + e.getMessage());
        }
    }

    public void delete(CartLine cartline) throws UserException {
    	 try {
             //save user object in the database
         	begin();
         	getSession().remove(cartline);
         	commit();
         	close();
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Exception while deleting cart line: " + e.getMessage());
         }
    }
    
    public void update(CartLine cartline) throws UserException {
   	     try {
            //save user object in the database
        	begin();
        	getSession().merge(cartline);
        	commit();
         	close();
        	
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while updating cart line: " + e.getMessage());
        }
   }
    
    public CartLine getCartItem(FoodItem fooditem, Customer customer)  throws UserException{
      try {
            
         	begin();
         	Query q = getSession().createQuery("from CartLine where customer=:customer AND fooditem=:fooditem");
         	//q.setString("username", username);
         	q.setParameter("customer", customer);
         	q.setParameter("fooditem", fooditem);
         	CartLine cartline=(CartLine)q.uniqueResult();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	if(cartline!=null)
         	{
         	return cartline;
         	}
         	else
         	{
         		return null;
         	}
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get cart line for " + fooditem, e);
         }
    }
}
