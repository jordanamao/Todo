package com.collabera.todoapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
	@RequestMapping(value = "/", method =RequestMethod.GET)
	public String welcomeMessage(ModelMap model) {
		model.put("name", getLogedinUserName());
		return "welcome";
	}
	
	private String getLogedinUserName() {
		
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		
		return principal.toString();
	}
}
