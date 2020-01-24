package com.collabera.todoapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.collabera.todoapp.model.Todo;
import com.collabera.todoapp.services.TodoServiceI;
import com.collabera.todoapp.services.TodoServiceRDBMS;

@Controller
public class TodoController {
	
	@Autowired
	TodoServiceI service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// dd/MM/yyyy
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value="/listtodos", method =RequestMethod.GET)
	public String listTodos(ModelMap model) {
		String name = getLogedinUserName(model);
		model.put("todos", service.listTodos(name));
		return "listtodos";
	}
	
	@RequestMapping(value="/todo", method = RequestMethod.GET)
	public String getAddTodo(ModelMap model) {
		Todo todo= new Todo(0,"",getLogedinUserName(model),new Date(),false);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		String name = getLogedinUserName(model);
		service.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/deletetodo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int todoId) {
		
		if(todoId == 1)
			throw new RuntimeException("Something went wrong");
		
		Todo todo = service.deleteTodo(todoId);
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/edittodo", method = RequestMethod.GET)
	public String showEditTodo(ModelMap model,@RequestParam int todoId) {
		Todo todo = service.getTodo(todoId);
		model.addAttribute("todo",todo);
		return "todo";
	}

	@RequestMapping(value="/edittodo", method = RequestMethod.POST)
	public String editTodo(ModelMap model, @Valid Todo todo,
			BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		//todo.setUser((String) model.get("name"));
		service.editTodo(todo);
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder
				.getContext().getAuthentication();
		
		if(auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);
		
		return "redirect:/";
	}

	private String getLogedinUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		
		return principal.toString();
	}

}
