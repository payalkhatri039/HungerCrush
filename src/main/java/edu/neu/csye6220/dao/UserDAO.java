package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.User;

@Component
public class UserDAO extends DAO {
	
    public UserDAO() {
    }

    public User get(String email) throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from User where email=:email");
         	//q.setString("username", username);
         	q.setParameter("email", email);
         	User user = (User) q.uniqueResult();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	return user;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not create user " + email, e);
         }
    	
    }
    public User create(User user) throws UserException {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(user);
        	commit();
        	close();	
        	return user;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user) throws UserException {
    	 try {
             //save user object in the database
         	begin();
         	getSession().remove(user);
         	commit();
         	close();
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Exception while deleting user: " + e.getMessage());
         }
    }
    
    public void update(User user) throws UserException {
   	 try {
            //save user object in the database
        	begin();
        	getSession().merge(user);
        	commit();
         	close();
        	
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while updating user: " + e.getMessage());
        }
   }
}