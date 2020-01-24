package com.collabera.todoapp.services;

import org.springframework.stereotype.Service;

@Service
public class LoginServise {
	
	public Boolean validateUser(String name, String password) {

		return name.equalsIgnoreCase("vijay") && password.equalsIgnoreCase("p");
	}

}
