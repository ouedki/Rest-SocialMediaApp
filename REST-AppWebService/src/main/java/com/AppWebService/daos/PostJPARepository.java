package com.AppWebService.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppWebService.models.Post;

public interface PostJPARepository extends JpaRepository<Post, Long> {

}
