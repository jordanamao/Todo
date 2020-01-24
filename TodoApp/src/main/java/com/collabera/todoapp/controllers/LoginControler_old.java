package com.collabera.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.collabera.todoapp.services.LoginServise;


@SessionAttributes("name")
public class LoginControler_old {

	// /login
	@Autowired
	LoginServise logSvc;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) // get post
	// string
	// view html response and send it back
	// view resolver which is managed by spring
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validateLogin(@RequestParam(defaultValue="guest") String name,
			@RequestParam String password,
			ModelMap model) {
		
	   if(!logSvc.validateUser(name, password)) {
		   return "login";
	   }
	   else {
		model.put("name", name);
		return "welcome";
	   }
	}
}
