package com.collabera.todoapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ConnectionManager {

	String connectionString="jdbc:mysql://localhost:3306/TodoJDBC";
	String username="root";
	String password="991335323";

	Connection myConn;

	public ConnectionManager() {
		super();

		try {
			myConn = DriverManager.getConnection(connectionString, username, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getMyConn() {

		if(myConn!=null) 

			return myConn;

		else {

			try {
				myConn = DriverManager.getConnection(connectionString, username, password);
				return myConn;

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
