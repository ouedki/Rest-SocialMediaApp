package com.AppWebService.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.AppWebService.models.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private long idCounter= 3;
	
	static {
//		users.add(new User(1L, "yassine","yassine@gmail.com" , new Date()));
//		users.add(new User(2L, "Adam","adam@gmail.com" , new Date()));
//		users.add(new User(3L, "Tony","tony@gmail.com" , new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if (user.getId()==null) {
			user.setId(++idCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(Long id) {
		for(User user : users) {
			if (user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(Long id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId()==id) {
				iterator.remove();
				return user;
			}
		}

		return null;
	}
	
	public MappingJacksonValue filterUsers(Object o, String...strings ){
		MappingJacksonValue mapping = new MappingJacksonValue(o);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(strings);
		FilterProvider filters = new SimpleFilterProvider().addFilter("MyFilter", filter );
		mapping.setFilters(filters );
		return mapping;
	}
}
