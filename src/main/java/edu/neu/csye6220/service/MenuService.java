package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.MenuDAO;
import edu.neu.csye6220.exception.UserException;
import edu.neu.csye6220.model.FoodItem;

@Service("MenuService")
public class MenuService {
	
	private MenuDAO menuDao;
	
	@Autowired
	public MenuService(MenuDAO menuDao)
	{
		this.menuDao=menuDao;
	}
	
	public void saveFoodItem(FoodItem fooditem)
	{
		try {
			menuDao.create(fooditem);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<FoodItem> getAllFoodItems()
	{
		try {
			return menuDao.getAll();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteFoodItem(FoodItem fooditem)
	{
		try {
			menuDao.delete(fooditem);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FoodItem getFoodItem(String foodId) 
	{
		try {
			return menuDao.get(foodId);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateFoodItem(FoodItem fooditem)
	{
		try {
			menuDao.update(fooditem);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
