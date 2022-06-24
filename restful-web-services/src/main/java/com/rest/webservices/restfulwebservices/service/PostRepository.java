package com.rest.webservices.restfulwebservices.service;

import org.springframework.data.repository.CrudRepository;

import com.rest.webservices.restfulwebservices.bean.Post;

public interface PostRepository extends CrudRepository<Post, Integer>{


}
