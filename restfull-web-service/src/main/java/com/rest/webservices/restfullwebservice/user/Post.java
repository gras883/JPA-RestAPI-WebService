package com.rest.webservices.restfullwebservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer Id;
	private String Description;
	
	// Multiple post can be posted by the same user
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [Id=" + Id + ", Description=" + Description + "]";
	}
	

}
