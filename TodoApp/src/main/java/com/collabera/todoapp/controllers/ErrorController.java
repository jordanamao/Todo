package com.collabera.todoapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	
	// dispacter servlet gets an exception 
	@ExceptionHandler(Exception.class)
	public ModelAndView errorHandler(HttpServletRequest req,
			Exception ex) {
		
		ModelAndView mav= new ModelAndView();
		
		// write information to model
		mav.addObject("Exception", ex.getStackTrace());
		mav.addObject("Url", req.getRequestURL());
		System.out.println(req.getRequestURL());
		mav.setViewName("error");
		
		return mav;
	}
}
