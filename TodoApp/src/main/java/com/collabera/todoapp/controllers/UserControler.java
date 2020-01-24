package com.collabera.todoapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.collabera.todoapp.model.User;
import com.collabera.todoapp.services.UserService;

@Controller
public class UserControler {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users", method= RequestMethod.GET)
	public String listUsers(ModelMap model) {
		
		
		List<User> users = userService.listUsers( );
		
		model.put("users", users);
		
		return "listusers";
	}
	
	@RequestMapping(value="/adduser", method= RequestMethod.GET)
	public String showAddUser(ModelMap model) {
		
		model.put("user", new User(0,"" , "", "",""));
		
		return "adduser";
	}
	
	@RequestMapping(value="/adduser", method= RequestMethod.POST)
	public String addUser(ModelMap model, @Valid User user, BindingResult result ) {

		if(result.hasErrors())
			return "adduser";
		
		userService.addUser(user.getUserName(),user.getPassword(),user.getFullName());
		return "redirect:/users";
	}
	
	@RequestMapping(value="/deleteuser", method= RequestMethod.GET)
	public String deleteUser(ModelMap model, @RequestParam String userId) {
		userService.deleteUser(Integer.parseInt(userId));
		return "redirect:/users";
	}
	
	
	@RequestMapping(value="/updateuser", method= RequestMethod.GET)
	public String showUpdateUser(ModelMap model, @RequestParam String userId) {
		
		model.put("user",userService.getUser(Integer.parseInt(userId)));
		
		return "adduser";
	}
	
	@RequestMapping(value="/updateuser", method= RequestMethod.POST)
	public String updateUser(ModelMap model, @Valid User user, BindingResult result ) {

		System.out.println(user.toString());
		
		if(result.hasErrors())
			return "adduser";
		
		userService.updateUser(user);
		return "redirect:/users";
	}
}
