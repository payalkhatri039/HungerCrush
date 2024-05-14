package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.neu.csye6220.dao.UserDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.User;

@Service("UserService")
public class UserService {
 
	private UserDAO userDao;
	
	@Autowired
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public void saveUser(User user) {
		 
		try {
			userDao.create(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean findByEmail(String email) {
		try {
			User user = userDao.get(email);
			System.out.println("found="+user);
			if(user==null)
				{
				return false;
				}
			else {
				return true;
				}
		
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
}

	public User getByEmail(String email) {
		User user = new User();
		try {
			 user = userDao.get(email);
			//System.out.println("found="+user);
			
		
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
}
	public String getUserRole(String email) {
		User user = new User();
		try {
			 user = userDao.get(email);
			//System.out.println("found="+user);
			
		
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user.getRole();
}
	
	public boolean checkPassword(String email, String password) {
		try {
			if(findByEmail(email))
			{
				User user = userDao.get(email);
				System.out.println("found="+user);
				System.out.println(user.getPassword());
				if(user.getPassword().contentEquals(password)) {
					return true;
					}
				else {
					return false;
				}	
			}
			
		
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
}
}