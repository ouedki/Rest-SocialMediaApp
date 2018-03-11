package com.AppWebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.AppWebService.daos.UserJPARepository;
import com.AppWebService.models.CustomUserDetails;
import com.AppWebService.models.User;

@SpringBootApplication
public class RestAppWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAppWebServiceApplication.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserJPARepository userRepo) throws Exception {
		builder.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = userRepo.findByUsername(username);
				return new CustomUserDetails(user);
			}
		});
	}
}
