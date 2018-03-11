package com.AppWebService.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonFilter("MyFilter")
@ApiModel(description="User model")
@Entity
public class User {
	@Id @GeneratedValue
	private Long id;
	@ApiModelProperty(notes="Name should contain between 2 and 8 characters")
	@NotEmpty
	@Size(min = 2, max = 8, message="Name should be atleast 2 characters")
	private String name;
	private String username;
	private String password;
	
	@ApiModelProperty(notes="Email should be in a valid email format")
	@NotNull
    @Email(message="Valid email email@mail.com")
	private String email;
	@ApiModelProperty(notes="DOB can not be in the past")
	@Past(message="DOB can not be in the Future")
	private Date dateOfBirth;
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	@OneToMany(mappedBy="role", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User(Long id, String name, String username, String password, String email, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
