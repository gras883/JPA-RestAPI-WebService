package com.rest.webservices.restfullwebservice.user;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;


@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	@Size(min=2)
	private String Name;
	
	@Past
	private Date Dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	
	
	static int count = 0;
	public User() {
	
	}
	
	public User(int id, String name, Date dob) {
		super();
		Id = id;
		Name = name;
		Dob = dob;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setId(Integer id) {
		Id = id;
	}

}
