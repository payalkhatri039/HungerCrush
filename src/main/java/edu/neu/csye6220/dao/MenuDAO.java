package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.CartLine;
import edu.neu.csye6220.model.FoodItem;

@Component
public class MenuDAO extends DAO{

	public MenuDAO() {
		
	}
	
	public FoodItem create(FoodItem fooditem) throws UserException {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(fooditem);
        	commit();
        	close();
        	
        	return fooditem;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating food item: " + e.getMessage());
        }
    }
	
	 public List<FoodItem> getAll() throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from FoodItem");
         	List<FoodItem> fooditem =(List<FoodItem>) q.list();
         	System.out.println("get="+fooditem);
         	commit();
         	close();
         	return fooditem;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get all food items ", e);
         }
    	
    }
	 
	 public void delete(FoodItem fooditem) throws UserException {
    	 try {
             //save user object in the database
         	begin();
         	Query q = getSession().createQuery("delete from CartLine where fooditem=:fooditem");
         	q.setParameter("fooditem", fooditem);
         	q.executeUpdate();
         	getSession().remove(fooditem);
         	commit();
         	close();
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Exception while deleting food item: " + e.getMessage());
         }
    }
	 
	 public FoodItem get(String foodId) throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from FoodItem where foodId=:foodId");
         	//q.setString("username", username);
         	q.setParameter("foodId", foodId);
         	FoodItem fooditem = (FoodItem) q.uniqueResult();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	return fooditem;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get food item with id : " + foodId, e);
         }
    	
    }
	 
	 public void update(FoodItem fooditem) throws UserException {
	   	 try {
	            //save user object in the database
	        	begin();
	        	getSession().merge(fooditem);
	        	commit();
	        	close();
	        	
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException("Exception while updating food item: " + e.getMessage());
	        }
	   }
}
