package edu.neu.csye6220.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.csye6220.dao.CartLineDAO;
import edu.neu.csye6220.dao.CustomerDAO;
import edu.neu.csye6220.dao.MenuDAO;
import edu.neu.csye6220.dao.OrderDAO;
import edu.neu.csye6220.dao.OrderItemDAO;
import edu.neu.csye6220.model.CartLine;
import edu.neu.csye6220.model.Customer;
import edu.neu.csye6220.model.FoodItem;
import edu.neu.csye6220.model.Order;
import edu.neu.csye6220.model.OrderItem;
import edu.neu.csye6220.service.CustomerCartService;
import edu.neu.csye6220.service.CustomerService;
import edu.neu.csye6220.service.MenuService;
import edu.neu.csye6220.service.OrderItemService;
import edu.neu.csye6220.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CustomerCartController {
	
	
	public boolean checkSession(HttpServletRequest request)
	{
		if((Customer)request.getSession().getAttribute("loggedCustomer")!=null)
		{
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	
	
	@RequestMapping(value={"/customer/menu"},
		    method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView customerDirection(HttpServletRequest request, MenuDAO menuDao)
	{
			ModelAndView modelandview = new ModelAndView();
			if(checkSession(request))
			{
				MenuService menuService= new MenuService(menuDao);
				modelandview.setViewName("/customerMenu");
				List<FoodItem> foodItemList=menuService.getAllFoodItems();
				HttpSession session = request.getSession(false);
				session.setAttribute("foodItemList", foodItemList);
				//modelandview.addObject("foodItemList",foodItemList);
				System.out.println("foodlist="+foodItemList);
			}
			else
			{
				modelandview.setViewName("loggedOut");
			}
			return modelandview;
	}
	
	
	
	@PostMapping("customer/menu/add")
	public ModelAndView addCartLine(@RequestParam(name="id") String selectedItemId,
			@RequestParam(name="quantity") Integer quantity, HttpServletRequest request,MenuDAO menuDao, CartLineDAO cartLineDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		MenuService menuService= new MenuService(menuDao);
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		modelandview.setViewName("/customerMenu");
		Customer customer =new Customer();
		HttpSession session = request.getSession(false);
		customer=(Customer)session.getAttribute("loggedCustomer");
		
		FoodItem fooditem=new FoodItem();
		fooditem=menuService.getFoodItem(selectedItemId);
//		System.out.println("customer="+customer);
//		System.out.println("its fooditem="+fooditem);
		CartLine cartline=customerCartService.checkCartItem(fooditem,customer);
		if(cartline==null)
		{
			 cartline=new CartLine(customer,fooditem, quantity);
			customerCartService.saveCartItem(cartline);
		}
		else
		{
			System.out.println("cartline="+cartline);
			cartline.setQuantity(quantity);
			customerCartService.updateCartItem(cartline);
		}
		modelandview.addObject("itemAddedMessage", "Item has been added to the cart");
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	
	
	@PostMapping("customer/menu/update")
	public ModelAndView updateCartLine(@RequestParam(name="id") String selectedItemId,
			@RequestParam(name="quantity") Integer quantity, HttpServletRequest request, MenuDAO menuDao, CartLineDAO cartLineDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		MenuService menuService= new MenuService(menuDao);
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		modelandview.setViewName("/viewCart");
		Customer customer =new Customer();
		HttpSession session = request.getSession(false);
		customer=(Customer)session.getAttribute("loggedCustomer");
		FoodItem fooditem=new FoodItem();
		fooditem=menuService.getFoodItem(selectedItemId);
//		System.out.println("customer="+customer);
		System.out.println("its fooditem="+fooditem);
		CartLine cartline=customerCartService.checkCartItem(fooditem,customer);
		System.out.println("cartline="+cartline);
		cartline.setQuantity(quantity);
		customerCartService.updateCartItem(cartline);
		List<CartLine> cartlines = customerCartService.getCustomerCartItems(customer);
		session.setAttribute("cartlines", cartlines);
		modelandview.addObject("itemAddedMessage", "Item has been updated the cart");
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	
	
	@PostMapping("customer/menu/delete")
	public ModelAndView deleteCartLine(@RequestParam(name="id") String selectedItemId,
	HttpServletRequest request, MenuDAO menuDao, CartLineDAO cartLineDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		MenuService menuService= new MenuService(menuDao);
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		modelandview.setViewName("/viewCart");
		Customer customer =new Customer();
		HttpSession session = request.getSession(false);
		customer=(Customer)session.getAttribute("loggedCustomer");
		FoodItem fooditem=new FoodItem();
		fooditem=menuService.getFoodItem(selectedItemId);
//		System.out.println("customer="+customer);
//		System.out.println("its fooditem="+fooditem);
		CartLine cartline=customerCartService.checkCartItem(fooditem,customer);
		System.out.println("cartline="+cartline);
		customerCartService.deleteCartItem(cartline);
		List<CartLine> cartlines = customerCartService.getCustomerCartItems(customer);
		session.setAttribute("cartlines", cartlines);
		modelandview.addObject("itemAddedMessage", "Item has been deleted from the cart");
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	
	
	@GetMapping("/customer/cart/viewCart")
	public ModelAndView viewCart(HttpServletRequest request, CartLineDAO cartLineDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		modelandview.setViewName("/viewCart");
		HttpSession session = request.getSession(false);
		Customer customer =new Customer();
		customer=(Customer)session.getAttribute("loggedCustomer");
		List<CartLine> cartlines = customerCartService.getCustomerCartItems(customer);
		session.setAttribute("cartlines", cartlines);
		System.out.println("carlines="+cartlines);
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	@GetMapping("/customer/cart/checkout")
	public ModelAndView orderCart(HttpServletRequest request, CartLineDAO cartLineDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		modelandview.setViewName("/checkout");
		HttpSession session = request.getSession(false);
		Customer customer =new Customer();
		customer=(Customer)session.getAttribute("loggedCustomer");
		List<CartLine> cartlines = customerCartService.getCustomerCartItems(customer);
		session.setAttribute("cartlines", cartlines);
		System.out.println("checkout carlines="+cartlines);
		int totalCost=0;
		for(CartLine cartline:cartlines)
		{
		totalCost+=(cartline.getFooditem().getPrice()*cartline.getQuantity());
		}
		session.setAttribute("cartCost", totalCost);
		System.out.println("cartCost="+ totalCost);
		}
		else
		{
		modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	
	
	@PostMapping("/customer/ordered")
	public ModelAndView checkout(HttpServletRequest request, CartLineDAO cartLineDao,OrderDAO orderDao,OrderItemDAO orderItemDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		CustomerCartService customerCartService=new CustomerCartService(cartLineDao);
		OrderService orderService=new OrderService(orderDao);
		OrderItemService orderItemService = new OrderItemService(orderItemDao);
		modelandview.setViewName("/orderSuccessful");
		HttpSession session = request.getSession(false);
		Customer customer =new Customer();
		customer=(Customer)session.getAttribute("loggedCustomer");
		List<CartLine> cartlines = customerCartService.getCustomerCartItems(customer);
		session.setAttribute("cartlines", cartlines);
		List<OrderItem> orderitems=new ArrayList<>();
		double totalCost=0;
		Order order = new Order();
		orderService.saveOrder(order);
		System.out.println("orderid?="+ order.getOrderId());
		for(CartLine cartline:cartlines)
		{
			//double cartLineCost=cartline.getQuantity()* cartline.getFooditem().getPrice();
			OrderItem orderitem=new OrderItem(order,customer,cartline.getFooditem().getFoodName(),cartline.getFooditem().getPrice(),cartline.getQuantity());
			orderItemService.saveOrderItem(orderitem);
			orderitems.add(orderitem);
			totalCost+=(cartline.getFooditem().getPrice() * cartline.getQuantity());
			customerCartService.deleteCartItem(cartline);
		}
       long millis = System.currentTimeMillis();
       java.sql.Date date = new java.sql.Date(millis);        
       order.setCustomer(customer);
       order.setDate(date);
       order.setTotalCost(totalCost);
       order.setOrderitems(orderitems);
       orderService.updateOrder(order);
		}
		else
		{
		modelandview.setViewName("loggedOut");
		}
	   return modelandview;
	}

	
	
	@GetMapping("/customer/viewPastOrders")
	public ModelAndView viewPastOrders(HttpServletRequest request, CartLineDAO cartLineDao,OrderDAO orderDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		OrderService orderService=new OrderService(orderDao);
		modelandview.setViewName("viewPastOrders");
		Customer customer =(Customer) request.getSession().getAttribute("loggedCustomer");
//		Customer customer =new Customer();
//		customer=(Customer)session.getAttribute("loggedCustomer");
		List<Order> orders = orderService.getCustomerOrders(customer);
//		session.setAttribute("", orders);
		
		modelandview.addObject("pastOrders", orders);
		
		System.out.println("checkout pastOrders="+orders);
		}
		else
		{
		modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
	
	@GetMapping("/customer/viewProfile")
	public ModelAndView viewProfile(HttpServletRequest request, CustomerDAO customerDao) {
		ModelAndView modelandview = new ModelAndView();
		if(checkSession(request))
		{
		CustomerService customerService=new CustomerService(customerDao);
		modelandview.setViewName("customerViewProfile");
		}
		else
		{
			modelandview.setViewName("loggedOut");
		}
		return modelandview;
	}
	
//	@GetMapping("/customer/editProfile")
//	public ModelAndView editProfile(HttpServletRequest request, CustomerDAO customerDao) {
//		ModelAndView modelandview = new ModelAndView();
//		if(checkSession(request))
//		{
//		CustomerService customerService=new CustomerService(customerDao);
//		modelandview.setViewName("customerEditProfile");
//		}
//		else
//		{
//			modelandview.setViewName("loggedOut");
//		}
//		return modelandview;
//	}
}
