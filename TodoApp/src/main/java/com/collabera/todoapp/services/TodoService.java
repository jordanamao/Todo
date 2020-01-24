package com.collabera.todoapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.collabera.todoapp.model.Todo;


@Primary
public class TodoService implements TodoServiceI{
	
	// in memory database
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoId = 4;
	static {
		todos.add(new Todo(1, "Shopping", "vijay", new Date(), false));
		todos.add(new Todo(2, "Study", "vijay", new Date(), false));
		todos.add(new Todo(3, "run", "vijay", new Date(), false));
	}
	
	// returns all todos 
	public List<Todo> listAllTodos(){
		return todos;
	}
	
	// return user specific todos 
	public List<Todo> listTodos(String user){
		
		List<Todo> userTodos = new ArrayList<Todo>();
		for(Todo todo: todos)
			if(todo.getUser().equalsIgnoreCase(user)) 
				userTodos.add(todo);
		return userTodos;
	}
	
	// return todo by id
	public Todo getTodo(int todoId) {
		Optional<Todo> tempTodo = todos.stream()
				.filter(todo->todo.getId() == todoId)
				.findFirst();
		return tempTodo.get();
	}
	
	// add a todo to list
	public Todo addTodo(String user, String desc, Date targetDate, boolean status){
		Todo todo = new Todo(todoId++, desc, user, targetDate, status);
		todos.add(todo);
		return todo;
	}

	// update a todo in the list
	public Todo editTodo(Todo todo){
		deleteTodo(todo.getId());
		todos.add(todo);
		return todo;
	}
	
	// delete a todo
	public Todo deleteTodo(int todoId){
		Optional<Todo> tempTodo = todos.stream()
				.filter(todo->todo.getId() == todoId)
				.findFirst();
		
		if(tempTodo.isPresent()) 
			todos.remove(tempTodo.get());
		
		return tempTodo.get();
	}
	
}
