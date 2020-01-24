package com.collabera.todoapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.collabera.todoapp.services.UserService;

@Configuration
public class IMSecurtiyConfiguration extends WebSecurityConfigurerAdapter {
	// Users details
	@Autowired 
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
	throws Exception{	
		// set of user details who can access the application
		// User name Password
			// 0. In memory user management
//		auth.inMemoryAuthentication().withUser("vijay")
//		.password(passwordEncoder().encode("p"))
//		.roles("USER","ADMIN");
//		
//		auth.inMemoryAuthentication().withUser("jai")
//		.password(passwordEncoder().encode("p"))
//		.roles("USER");
//		
//		auth.inMemoryAuthentication().withUser("admin")
//		.password(passwordEncoder().encode("p"))
//		.roles("ADMIN");
		
		
	// 1. Database 2. LDAP 3. Active Directory 4. SSO 5. SAML 6. OAUTH 7. JWT
		// 1. Encoding
			// a. Algorithm to secure your data -> Base64Encoding Pattern
		// 2. Encryption 
			// a. Algorithm to secure your data by having specify key -
			// b. public and private key
		
		auth.userDetailsService(new UserService()).passwordEncoder(passwordEncoder());
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// how to access URL/Resources
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/","/login", "/webjars/**") // not loggdin anonusers 
			.permitAll()
		.antMatchers("/*todo*")  // http://localhost:9090/todo or /listtodos or /edittodo
			.hasAnyRole("USER", "ADMIN")
		.antMatchers("/**") // /user
			.hasAnyRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true) // welcome page
			.failureUrl("/login?error=true") // wrong credentials are given 
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/forbiden")
		.and()
			.csrf()
			.disable();
	}
	
	

}