package com.AppWebService.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AppWebService.daos.UserDaoService;
import com.AppWebService.daos.UserJPARepository;
import com.AppWebService.exceptions.UserNotFoundException;
import com.AppWebService.models.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired UserJPARepository userJPARepository;
	
	@GetMapping("/users")
	public MappingJacksonValue users(){
		List<User> listUsers = userJPARepository.findAll();
		MappingJacksonValue mapping = userDaoService.filterUsers(listUsers, "id", "name", "username" , "email", "dateOfBirth");
		return mapping;
	}
	
	@GetMapping("/users/{id}")
	public MappingJacksonValue getUserById(@PathVariable Long id){
		User user = userJPARepository.findOne(id);
		if (user==null) {
			throw new UserNotFoundException("No user with ID - "+id + "is found" );
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).users());
		resource.add(link.withRel("all-users"));
		MappingJacksonValue mapping = userDaoService.filterUsers(resource, "id", "name","username",  "dateOfBirth");
		return mapping;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> AddUser(@Valid @RequestBody User user){
		User savedUser = userJPARepository.save(user);
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id){
		userJPARepository.delete(id);
	
	}
}
