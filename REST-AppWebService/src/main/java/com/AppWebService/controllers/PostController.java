package com.AppWebService.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AppWebService.daos.PostJPARepository;
import com.AppWebService.daos.UserDaoService;
import com.AppWebService.daos.UserJPARepository;
import com.AppWebService.exceptions.UserNotFoundException;
import com.AppWebService.models.Post;
import com.AppWebService.models.User;

@RestController 
public class PostController {

	@Autowired
	private UserJPARepository userJpaRepository;
	
	@Autowired
	private PostJPARepository postJpaRepository;
	
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users/{id}/posts")
	public MappingJacksonValue posts (@PathVariable Long id) {
		User user = userJpaRepository.findOne(id);
		if (user==null) {
			throw new UserNotFoundException("No user with ID - "+id + " is found" );}
		List<Post> posts = user.getPosts();
		MappingJacksonValue mapping = userDaoService.filterUsers(posts, "id", "title", "description", "date");
		return mapping;
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> savePost (@Valid @PathVariable Long id, @RequestBody Post post) {
		User user = userJpaRepository.findOne(id);
		if (user==null) {
			throw new UserNotFoundException("No user with ID - "+id + " is found" );}
		post.setUser(user);
		Post newPost = postJpaRepository.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newPost.getId())
				.toUri();
				
				return ResponseEntity.created(location).build();
	}
}
