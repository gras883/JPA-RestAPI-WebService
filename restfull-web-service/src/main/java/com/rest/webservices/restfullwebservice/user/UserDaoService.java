package com.rest.webservices.restfullwebservice.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users= new ArrayList<>();
	
	private static int count = 3;
	static {
		users.add(new User(1, "Ankit", new Date()));
		users.add(new User(2, "Anurag", new Date()));
		users.add(new User(3, "Koyel", new Date()));
	}
	
	
	// get the userList
	public List<User> findAll(){
		return users;
	}
	
	// get the user id
	
	public User findone(int id) {
		for(User u: users) {
			if(u.getId() == id) {
				return u;
			}	
		}
		return null;
	}
	
	
	// Add the new user
	public User addOne(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}	
	
	
	// Delete the user Id
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	
	

}
