package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.User;

@Component
public class CustomerDAO extends DAO {
	
    public CustomerDAO() {
    }

    public Customer getByUserObj(User user) throws UserException {
    	 try {
            
         	begin();
         	Query q = getSession().createQuery("from Customer where user=:user");
         	//q.setString("username", username);
         	q.setParameter("user", user);
         	Customer customer = (Customer) q.uniqueResult();
         	//System.out.println("get="+user);
         	commit();
         	close();
         	return customer;
         	
         } catch (HibernateException e) {
             rollback();
             throw new UserException("Could not find customer " + user, e);
         }
    	
    }
    
    public Customer create(Customer customer) throws UserException {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(customer);
        	commit();
        	close();
        	
        	return customer;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating customer: " + e.getMessage());
        }
    }

//    public void delete(Customer customer) throws UserException {
//    	 try {
//             //save user object in the database
//         	begin();
//         	getSession().remove(customer);
//         	commit();
//         	
//         } catch (HibernateException e) {
//             rollback();
//             throw new UserException("Exception while deleting customer: " + e.getMessage());
//         }
//    }
    
//    public void update(Customer customer) throws UserException {
//   	 try {
//            //save user object in the database
//        	begin();
//        	getSession().merge(customer);
//        	commit();
//        	
//        } catch (HibernateException e) {
//            rollback();
//            throw new UserException("Exception while updating customer: " + e.getMessage());
//        }
//  }
}
