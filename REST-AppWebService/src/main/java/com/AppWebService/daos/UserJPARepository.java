package com.AppWebService.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppWebService.models.User;

public interface UserJPARepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
