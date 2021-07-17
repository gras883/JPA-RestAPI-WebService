package com.rest.webservices.restfullwebservice.user;

import java.net.URI;
//import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	// Get  /users
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return service.findAll();
	}
	

	//Get /users/Id
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findone(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user;
	}

	
	// POST /user
	// Use RestClient to post - add the record
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.addOne(user);
		
		// creat the URI for the location
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		// Reponse entity help to response 
		return ResponseEntity.created(location).build();
	}
	
	
	//Delete /users/Id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
