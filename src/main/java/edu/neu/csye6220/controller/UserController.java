package edu.neu.csye6220.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import edu.neu.csye6220.dao.CustomerDAO;
import edu.neu.csye6220.dao.RestaurantDAO;
import edu.neu.csye6220.dao.UserDAO;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.Restaurant;
import edu.neu.csye6220.model.User;
import edu.neu.csye6220.service.CustomerService;
import edu.neu.csye6220.service.RestaurantService;
import edu.neu.csye6220.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	public boolean checkLogin(String email, String password)
	{
		if(email.equals("null") || password.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@GetMapping("/home")
	public ModelAndView homePage() {
		ModelAndView modelandview = new ModelAndView();
		 modelandview.setViewName("home");
		 return modelandview;
	}	
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView modelandview = new ModelAndView();
		 modelandview.setViewName("login");
		 return modelandview;
	}
	
	
	@GetMapping("/signup")
	public ModelAndView signUpPage() {
		ModelAndView modelandview = new ModelAndView();
		User user = new User();
		modelandview.addObject("user", user);
		modelandview.setViewName("signup");
		return modelandview;
	}
	
	
	@PostMapping(path =  "/signup.htm")
	public ModelAndView signUpPost(@Valid @ModelAttribute("user")User user, 
			BindingResult bindingResult, UserDAO userDao,
			CustomerDAO customerDao, RestaurantDAO restaurantDao) 
	{

		ModelAndView modelandview = new ModelAndView();
		try {
			if (bindingResult.hasErrors()) {   
				modelandview.setViewName("/signup");
				return modelandview;
		    } else {
				UserService userService = new UserService(userDao);
				CustomerService customerservice = new CustomerService(customerDao);
				RestaurantService restaurantservice = new RestaurantService(restaurantDao);
				
				modelandview.setViewName("/home");
				//User user=new User(fname, lname, email, contactNo, password, address, role);
				if(userService.findByEmail(user.getEmail()))
				{
					modelandview.addObject("userRegisteredMessage", "This email id has already been used. Please use another email id/login");
					modelandview.setViewName("/signup");
					return modelandview;
				}
				else {
					modelandview.addObject("user",user);
					userService.saveUser(user);
					
					if(user.getRole().equals("customer"))
					{
					User userObj=userService.getByEmail(user.getEmail());
					Customer customer=new Customer();
					customer.setUser(userObj);
					customerservice.saveCustomer(customer);
					modelandview.addObject("customer",customer);
					}
				
					else if(user.getRole().equals("restaurant"))
					{
				
					User userObj=userService.getByEmail(user.getEmail());
					Restaurant restaurant=new Restaurant();
					restaurant.setAbout(user.getAbout());
					restaurant.setUser(userObj);
					restaurantservice.saveRestaurant(restaurant);
					modelandview.addObject("restaurant",restaurant);
					}
				}
					modelandview.addObject("userRegisteredMessage", "You are registered. Please login");
			}
		}
		catch(Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		
			return modelandview;
	}
	
	
	@GetMapping("HomePage.htm")
	public ModelAndView RestaurantHome(HttpServletRequest request) 
	{
		ModelAndView modelandview = new ModelAndView();
		User loggedUser=new User();
		Restaurant loggedRestaurant=new Restaurant();
		Customer loggedCustomer=new Customer();
		HttpSession session = request.getSession();
		loggedRestaurant=(Restaurant)session.getAttribute("loggedRestaurant");
		loggedCustomer=(Customer)session.getAttribute("loggedCustomer");
		
			//if(userService.getUserRole(loggedUser.getEmail()).equalsIgnoreCase("restaurant"))
			if(loggedRestaurant!=null)
			{
				modelandview.setViewName("/restaurantHome");
			}
			else if(loggedCustomer!=null)
			{
				 modelandview.setViewName("/customerHome");				
			}
			else
			{
				modelandview.setViewName("/loggedOut");
			}
		
		return modelandview;
		
	}
	
	
	
//	@RequestMapping(value={"/login.htm"},
//		    method={RequestMethod.POST,RequestMethod.GET})
	@PostMapping("HomePage.htm")
	public ModelAndView loginPost(@RequestParam(name="email", required=true)String email,
			@RequestParam(name="password", required=true) String password, HttpServletRequest request, UserDAO userDao, CustomerDAO customerDao, RestaurantDAO restaurantDao) 
	{
		System.out.println("recieved email:"+email+ "recieved password:"+password);
		UserService userService = new UserService(userDao);
		CustomerService customerservice = new CustomerService(customerDao);
		RestaurantService restaurantservice = new RestaurantService(restaurantDao);
		
		ModelAndView modelandview = new ModelAndView();
		
		if(email.length()<7 || email.length()>50)
		{
			modelandview.addObject("errorMessage", "email length should be between 7-50");
			modelandview.setViewName("/login");
			return modelandview;
		}
		 if(password.length()<7 || password.length()>50)
		{
			modelandview.addObject("errorMessage", "password length should be between 7-50");
			modelandview.setViewName("/login");
			return modelandview;
		}
		
		 if(userService.checkPassword(email, password))
		{
			User user = new User();
			
			user=userService.getByEmail(email);
			HttpSession session = request.getSession(true);
			//System.out.println("User role is:"+ userService.getUserRole(email));
			if(userService.getUserRole(email).equalsIgnoreCase("restaurant"))
			{
				Restaurant restaurant = new Restaurant();
				restaurant=restaurantservice.getRestaurant(user);
				session.setAttribute("loggedRestaurant", restaurant);
				modelandview.setViewName("/restaurantHome");
			}
			else if(userService.getUserRole(email).equalsIgnoreCase("customer"))
			{
				Customer customer = new Customer();
				customer=customerservice.getCustomer(user);
				session.setAttribute("loggedCustomer", customer);
				 modelandview.setViewName("/customerHome");				
			}

		}
		else {
			 modelandview.setViewName("/loginFailure");

		}
		
		return modelandview;
		
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("home");
		HttpSession session = request.getSession(false);
		System.out.println("logout");
		session.invalidate();
		return modelandview;
	}	
	
	}
	
