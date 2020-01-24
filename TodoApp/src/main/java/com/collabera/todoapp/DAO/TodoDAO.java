package com.collabera.todoapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collabera.todoapp.model.Todo;

@Component
public class TodoDAO {

	@Autowired
	ConnectionManager connMgr;
	Connection myConn=null;
	// crud

	
	//insert
	public Todo insertTodo(String desc, String user, Date targetDate, boolean status) throws SQLException {
		
		PreparedStatement myStmt=null;
		ResultSet myRs=null;

		try {
			// 1 open the connection
			
			myConn = connMgr.getMyConn();


			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')



			// 2. Prepare statements

			String sql = "INSERT INTO todo (id,description,user,targetDate,status) VALUES (null,?,?,?,?)";
			myStmt=  myConn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			//			myStmt=  myConn.prepareStatement(sql);

			//			myStmt.setInt(1, 12);
			myStmt.setString(2, user);
			myStmt.setString(1, desc);
			java.sql.Date sqlDate = new java.sql.Date(targetDate.getTime());

			myStmt.setDate(3,sqlDate);
			myStmt.setBoolean(4, status);
			// 3. callable statements
			// stored procedures

			// 3 execute

			int result = myStmt.executeUpdate();

			ResultSet keyResultSet = myStmt.getGeneratedKeys();
			int newId = 0;
			if (keyResultSet.next()) {
				newId = (int) keyResultSet.getInt(1);
			}


			// 4 process

			if(result > 0)
				return new Todo(newId,user,desc,targetDate,status);

			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}


	public Todo updateTodo(int id, String desc, String user, Date targetDate, boolean status) throws SQLException {
	
		PreparedStatement myStmt=null;
		ResultSet myRs=null;

		try {
			// 1 open the connection

			myConn = connMgr.getMyConn();

			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')


			// 2. Prepare statements
			//			myStmt=  myConn.prepareStatement("insert into todo(id,user,desc,targetDate,status)"
			//					+ "values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

			myStmt = myConn.prepareStatement("UPDATE todo SET description = ?, user = ?,targetDate = ?,status = ? WHERE id = ?");

			myStmt.setString(1, desc);
			myStmt.setString(2, user);
			java.sql.Date sqlDate = new java.sql.Date(targetDate.getTime());

			myStmt.setDate(3, sqlDate);
			myStmt.setBoolean(4, status);
			myStmt.setInt(5,id);
			// 3. callable statements
			// stored procedures

			// 3 execute

			int result = myStmt.executeUpdate();

			// 4 process

			if(result > 0)
				return new Todo(id,desc,user,targetDate,status);

			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}

	public Todo deleteTodo(int id) throws SQLException {
		
		PreparedStatement myStmt=null;
		ResultSet myRs=null;

		try {
			// 1 open the connection
			myConn = connMgr.getMyConn();

			Todo todo = getTodo(id);

			

			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')


			// 2. Prepare statements
			//			myStmt=  myConn.prepareStatement("insert into todo(id,user,desc,targetDate,status)"
			//					+ "values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

			myStmt = myConn.prepareStatement("DELETE FROM todo WHERE id = ?");

			myStmt.setInt(1, id);
			// 3. callable statements
			// stored procedures

			// 3 execute

			int result = myStmt.executeUpdate();

			// 4 process

			if(result > 0) {

				ResultSet resultSet = myStmt.getResultSet();
				return todo;
				//				return new Todo(id,user,desc,targetDate,status);
			}


			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}

	public Todo getTodo(int id) throws SQLException {
		
		PreparedStatement myStmt=null;

		try {
			// 1 open the connection

			myConn = connMgr.getMyConn();

			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')


			// 2. Prepare statements
			//			myStmt=  myConn.prepareStatement("insert into todo(id,user,desc,targetDate,status)"
			//					+ "values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

			myStmt = myConn.prepareStatement("Select * from todo where id = ?");

			myStmt.setInt(1, id);
			// 3. callable statements
			// stored procedures

			// 3 execute

			ResultSet result = myStmt.executeQuery();

			// 4 process

			if(result != null) {

				while(result.next()) {
					String user = result.getString("user");
					String desc = result.getString("description");
					Date targetDate = result.getDate("targetDate");
					Boolean status = result.getBoolean("status");
					return new Todo(id,desc,user,targetDate,status);
				}

			}


			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}

	public ArrayList<Todo> getUserTodos(String user) throws SQLException {
	
		PreparedStatement myStmt=null;
		ResultSet myRs=null;

		try {
			// 1 open the connection

			myConn = connMgr.getMyConn();

			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')


			// 2. Prepare statements
			//			myStmt=  myConn.prepareStatement("insert into todo(id,user,desc,targetDate,status)"
			//					+ "values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

			myStmt = myConn.prepareStatement("Select * from todo where user = ?");

			myStmt.setString(1, user);
			// 3. callable statements
			// stored procedures

			// 3 execute

			ResultSet result = myStmt.executeQuery();

			// 4 process
			ArrayList<Todo> todoList = new ArrayList<Todo>();
			if(result != null) {

				while(result.next()) {
					Todo todo = new Todo();
					int id = result.getInt("id");
					String desc = result.getString("description");
					String user1 = result.getString("user");
					Date targetDate = result.getDate("targetDate");
					Boolean status = result.getBoolean("status");
					todo = new Todo(id, desc, user1, targetDate, status);
					todoList.add(todo);
				}


				return todoList;
			}


			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}


	public ArrayList<Todo> getTodos() throws SQLException {
		
		PreparedStatement myStmt=null;
		ResultSet myRs=null;

		try {
			// 1 open the connection

			// 2 prepare sql statement
			// 1. Statement 
			// insert into todo('Columun names') values ('')


			// 2. Prepare statements
			//			myStmt=  myConn.prepareStatement("insert into todo(id,user,desc,targetDate,status)"
			//					+ "values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

			myStmt = myConn.prepareStatement("Select * from todo");

			// 3. callable statements
			// stored procedures

			// 3 execute

			ResultSet result = myStmt.executeQuery();

			// 4 process
			ArrayList<Todo> todoList = new ArrayList<Todo>();
			if(result != null) {

				while(result.next()) {
					Todo todo = new Todo();
					int id = result.getInt("id");
					String desc = result.getString("description");
					String user1 = result.getString("user");
					Date targetDate = result.getDate("targetDate");
					Boolean status = result.getBoolean("status");
					todo = new Todo(id, desc, user1, targetDate, status);
					todoList.add(todo);
				}


				return todoList;
			}


			// 5 close connection
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(myStmt!=null)
				myStmt.close();
			if(myConn!= null)
				myConn.close();
		}
		return null;
	}
}
