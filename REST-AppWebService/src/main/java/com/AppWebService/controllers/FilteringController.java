package com.AppWebService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AppWebService.daos.UserDaoService;
import com.AppWebService.models.User;


@RestController
public class FilteringController {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/filter-email")
	public MappingJacksonValue users(){
		List<User> listUsers = userDaoService.findAll();
		MappingJacksonValue mapping = userDaoService.filterUsers(listUsers, "id", "name", "email");
		return mapping;
	}
}
