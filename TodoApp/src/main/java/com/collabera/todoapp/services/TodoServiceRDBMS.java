package com.collabera.todoapp.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.collabera.todoapp.DAO.TodoDAO;
import com.collabera.todoapp.model.Todo;

@Service
public class TodoServiceRDBMS implements TodoServiceI {
	
	@Autowired
	TodoDAO todoDAO;
	
	// returns all todos 
	public List<Todo> listAllTodos() throws SQLException{
		
		return todoDAO.getTodos();
	}
	
	// return user specific todos 
	public List<Todo> listTodos(String user){
		
		List<Todo> userTodos = new ArrayList<Todo>();
		return userTodos;
	}
	
	// return todo by id
	public Todo getTodo(int todoId) {
		try {
			Todo todo = todoDAO.getTodo(todoId);
			return todo;
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		return null;
	}
	
	// add a todo to list
	public Todo addTodo(String user, String desc, Date targetDate, boolean status) {
		try {
		Todo todo = todoDAO.insertTodo(desc, user, targetDate, status);
		return todo;
		}
		catch(SQLException e) {
			
		}
		return null;
	}

	// update a todo in the list
	public Todo editTodo(Todo todo){
		
		try {
		    return todoDAO.updateTodo(todo.getId(),todo.getDesc(),todo.getUser(), todo.getTargetDate(),todo.isStatus());

			}
			catch(SQLException e) {
				
			}
			return null;
	}
	
	// delete a todo
	public Todo deleteTodo(int todoId){
		
		try {
		    return todoDAO.deleteTodo(todoId);

			}
			catch(SQLException e) {
				
			}
			return null;
	}
	
}
