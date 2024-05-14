package edu.neu.csye6220.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorHandlerController implements ErrorController{

@GetMapping("/handleError")
public ModelAndView errorHandler()
{
	ModelAndView modelandview=new ModelAndView();
	modelandview.setViewName("error");
	return modelandview;
}
}
