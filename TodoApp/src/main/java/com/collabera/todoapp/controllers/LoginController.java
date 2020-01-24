package com.collabera.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET) // get post
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			ModelMap model) {
		String errorMessage = null;
		
		if(error!=null)
			errorMessage="Wrong Credentials";
		if(logout!=null)
			errorMessage="You have successfully logged out !!";

		model.put("errorMessage", errorMessage);
		return "loginSecure";
	}
	
	@RequestMapping(value = "/forbiden", method = RequestMethod.GET) // get post
	public String login()
	{
		return "forbiden";
	}
		
	
	
}
