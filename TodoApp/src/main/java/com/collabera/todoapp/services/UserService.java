package com.collabera.todoapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.collabera.todoapp.model.User;

@Service
public class UserService implements UserDetailsService {

	// CRUD

	private static List<User> users = new ArrayList<User>();
	
	private static int userCount=2;
	
	static {
		users.add(new User(1, "jordan",  "12345", "Jordan Mao", "ADMIN" ));
		users.add(new User(2, "hannah",  "12345", "Hannah Mao", "ADMIN"));
	}
	
	// read admin
		public List<User> listUsers(){
	        return users;
		}
		
		public User getUserByName(String userName){
			 for (User user : users) {
	            if (user.getUserName().equals(userName)) {
	                return user;
	            }
	        }
	        return null;
		}
		
		public User getUser(int id){
			 for (User User : users) {
	            if (User.getId() == id) {
	                return User;
	            }
	        }
	        return null;
		}
		
		// create
		public User addUser(String userName, String password,String fullname) {
			User newUser = new User(++userCount, userName,  password,  fullname, "USER" );
			users.add(newUser);
			return newUser;
		}
		
		// delete
		public User deleteUser(int userId) {
			User deleteUser = users.stream()
					  .filter(user -> user.getId() == userId)
					  .findFirst()
					  .orElse(null);
			users.remove(deleteUser);
			return deleteUser;
		}

		public User updateUser(User user) {
			deleteUser(user.getId());
			users.add(user);
			return user;
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			User user= getUserByName(username);
			
			UserBuilder userBuilder =null;
			UserDetails userDetails = null;
			
			if(user!=null) {
				userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUserName());
				userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
				userBuilder.roles(user.getRoles());
				
				userDetails = userBuilder.build();
				
				return userDetails;
				
			}
			else {
				throw new UsernameNotFoundException("User Does not Exsits");
				}
		}

		
}