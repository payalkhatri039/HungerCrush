package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Restaurant;
import edu.neu.csye6220.model.User;

@Component
public class RestaurantDAO extends DAO {
	
public RestaurantDAO() {
	
}

	public Restaurant create(Restaurant restaurant) throws UserException {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(restaurant);
        	commit();
        	close();
        	
        	return restaurant;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating restaurant: " + e.getMessage());
        }
    }
	
	public Restaurant getByUserObj(User user) throws UserException {
		 try {
	       
	    	begin();
	    	Query q = getSession().createQuery("from Restaurant where user=:user");
	    	//q.setString("username", username);
	    	q.setParameter("user", user);
	    	Restaurant restaurant = (Restaurant) q.uniqueResult();
	    	//System.out.println("get="+user);
	    	commit();
         	close();
	    	return restaurant;
	    	
	    } catch (HibernateException e) {
	        rollback();
	        throw new UserException("Could not find restaurant " + user, e);
	    }
		
	}
}
