package edu.neu.csye6220.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.csye6220.dao.MenuDAO;
import edu.neu.csye6220.dao.OrderDAO;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.Order;
import edu.neu.csye6220.model.Restaurant;
import edu.neu.csye6220.pdf.GeneratePDF;
import edu.neu.csye6220.service.MenuService;
import edu.neu.csye6220.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MenuController {
	

	public boolean checkSession(HttpServletRequest request)
	{
		if((Restaurant)request.getSession().getAttribute("loggedRestaurant")!=null)
		{
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	
	//@GetMapping("/restaurant/menu")
	@RequestMapping(value={"/restaurant/menu"},
    method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView restaurantDirection( @RequestParam(name="restaurantAction")String restaurantAction,
		MenuDAO menuDao,HttpServletRequest request)
	{
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		MenuService menuservice= new MenuService(menuDao);
		List<FoodItem> foodItemList=menuservice.getAllFoodItems();
		//modelandview.addObject("foodItemList",foodItemList);
		request.getSession().setAttribute("foodItemList",foodItemList);
		if(restaurantAction.contentEquals("addFood"))
		{
			modelandview.addObject("fooditem", new FoodItem());
			 modelandview.setViewName("/addFoodItem");

		}
		else if(restaurantAction.contentEquals("viewFood"))
		{
			modelandview.setViewName("viewMenu");

		}
		else
		{
			modelandview.setViewName("viewDeleteUpdateFoodItem");

		}
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}

		return modelandview;
	}
	
	
	@PostMapping(path ="/restaurant/addFoodItem.htm")
	public ModelAndView addFoodItem(@Valid @ModelAttribute("fooditem")FoodItem fooditem, 
			BindingResult bindingResult, MenuDAO menuDao, 
			HttpServletRequest request)
	{
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
			if (bindingResult.hasErrors()) {       
				modelandview.setViewName("/addFoodItem");
				return modelandview;
		    }
		modelandview.setViewName("foodItemAddedSuccessful");
		MenuService menuservice= new MenuService(menuDao);
		System.out.println(fooditem.getFoodName());
		System.out.println(fooditem.getDescription());
		System.out.println(fooditem.getPrice());
		menuservice.saveFoodItem(fooditem);
		modelandview.addObject("fooditem", fooditem);
		List<FoodItem> foodItemList=menuservice.getAllFoodItems();
		request.getSession().setAttribute("foodItemList",foodItemList);
		modelandview.addObject("addedMessage", "Food Item added");
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
}
	
	@GetMapping("/restaurant/menu/action")
	public ModelAndView viewAndEditMenu(@RequestParam(name="itemAction") String itemAction,
			@RequestParam(name="id") String selectedItemId, MenuDAO menuDao,
			HttpServletRequest request)
	{
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		FoodItem fooditem=new FoodItem();
		MenuService menuservice= new MenuService(menuDao);
		fooditem.setFoodId(Integer.parseInt(selectedItemId));
		if(itemAction.equals("delete"))
		{
			menuservice.deleteFoodItem(fooditem);
		}
		modelandview.setViewName("viewDeleteUpdateFoodItem");
		List<FoodItem> foodItemList=menuservice.getAllFoodItems();
		request.getSession().setAttribute("foodItemList",foodItemList);
		System.out.println("foodlist="+foodItemList);
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;	
	}
	
	
	@GetMapping("/restaurant/menu/update")
	public ModelAndView UpdateMenu(@RequestParam(name="id") String selectedItemId,
			MenuDAO menuDao, HttpServletRequest request)
	{
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		modelandview.setViewName("updateMenu");
		MenuService menuservice= new MenuService(menuDao);
		FoodItem fooditem=new FoodItem();
		fooditem=menuservice.getFoodItem(selectedItemId);
		//modelandview.addObject("updateitem", fooditem);
		request.getSession().setAttribute("updateitem", fooditem);
		List<FoodItem> foodItemList=menuservice.getAllFoodItems();
		//modelandview.addObject("foodItemList",foodItemList);
		request.getSession().setAttribute("foodItemList",foodItemList);
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;	
	}

	
	@PostMapping("/restaurant/menu/updatedetails")
	public ModelAndView UpdatingMenu(@RequestParam(name="id") String selectedItemId,
	@RequestParam(name="newFoodName") String newFoodName,
	@RequestParam(name="newDescription")String newDescription,
	@RequestParam(name="newPrice")String newPrice, MenuDAO menuDao,
	HttpServletRequest request)
	{
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
	    MenuService menuservice= new MenuService(menuDao);
		FoodItem fooditem=new FoodItem();
		fooditem=menuservice.getFoodItem(selectedItemId);
		System.out.println("selectedItem to update="+fooditem);
		System.out.println("newFoodName="+newFoodName);
		System.out.println("newDescription="+newDescription);
		System.out.println("newDescription="+newPrice);

		if(newFoodName!="")
		{
			if(newFoodName.length()<3|| newFoodName.length()>50)
			{
				modelandview.addObject("errorMessage", "Please enter food name of between size 3-50");
				modelandview.setViewName("updateMenu");
				return modelandview;
			}
			fooditem.setFoodName(newFoodName);
		}
		if(newDescription!="")
		{
			if(newDescription.length()<3|| newDescription.length()>50)
			{
				modelandview.addObject("errorMessage", "Please enter food description of between size 3-50");
				modelandview.setViewName("updateMenu");
				return modelandview;
			}
			fooditem.setDescription(newDescription);
		}
		if(newPrice!="")
		{
			double newprice=Double.parseDouble(newPrice);
			if(newprice<0|| newprice>1000)
			{
				modelandview.addObject("errorMessage", "Please enter food price of between 0-1000");
				modelandview.setViewName("updateMenu");
				return modelandview;
			}
			fooditem.setPrice(newprice);
		}
		menuservice.updateFoodItem(fooditem);
		modelandview.setViewName("foodItemUpdateSuccessful");
		System.out.println("updated item="+fooditem);
		modelandview.addObject("updateitem", fooditem);
		List<FoodItem> foodItemList=menuservice.getAllFoodItems();
		request.getSession().setAttribute("foodItemList",foodItemList);
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;	
	}

	
	@GetMapping("restaurant/viewAllOrders")
	public ModelAndView viewAllOrders(HttpServletRequest request, OrderDAO orderDao)
	{
		ModelAndView modelandview=new ModelAndView();
		if(checkSession(request))
		{
			OrderService orderService=new OrderService(orderDao);
			modelandview.setViewName("restaurantViewAllOrders");
			List<Order> allOrders = orderService.getAllOrders();
			modelandview.addObject("allOrders", allOrders);
			System.out.println("all present orders="+allOrders);
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		
		return modelandview;
	}
	
	@GetMapping("/restaurant/getPdf")
	public ModelAndView getPdf(HttpServletRequest request, OrderDAO orderDao, Model model)
	{

		ModelAndView modelandview=new ModelAndView();
		if(checkSession(request))
		{
			OrderService orderService=new OrderService(orderDao);
			modelandview.setView(new GeneratePDF());
			List<Order> allOrders = orderService.getAllOrders(); 
			System.out.println("all present orders="+allOrders);
			model.addAttribute("allOrders", allOrders);
			return modelandview;
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
			return modelandview;
		
	}
	
}
