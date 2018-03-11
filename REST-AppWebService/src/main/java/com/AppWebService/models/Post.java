package com.AppWebService.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonFilter("MyFilter")
@ApiModel(description="Post model")
public class Post {
	@Id @GeneratedValue
	private Long id;
	@NotEmpty
	@ApiModelProperty(notes="Name should be at least 2 characters")
	@Size(min = 2, max = 20, message="Name should be atleast 2 characters")
	private String title;
	@NotNull
	private String description;
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Post(Long id, String title, String description, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
