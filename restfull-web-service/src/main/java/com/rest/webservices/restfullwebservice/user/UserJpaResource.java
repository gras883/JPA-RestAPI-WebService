package com.rest.webservices.restfullwebservice.user;

import java.net.URI;
//import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserJpaResource {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	// Get  /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser(){
		return userRepository.findAll();
	}
	

	//Get /users/Id
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		//User user = service.findone(id);
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user.get();
	}

	
	// POST /user
	// Use RestClient to post - add the record
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		//User savedUser = service.addOne(user);
		
		User saveUser = userRepository.save(user);
		
		// creat the URI for the location
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(saveUser.getId())
						.toUri();
		
		
		// Reponse entity help to response 
		return ResponseEntity.created(location).build();
	}
	
	
	//Delete /users/Id
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	// Get the post detail for the user
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id){
		//User user = service.findone(id);
		Optional<User> user = userRepository.findById(id);
				
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
		Optional<User> Optionaluser = userRepository.findById(id);
		
		if(!Optionaluser.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
		
		User user = Optionaluser.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.
					   fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	

}
