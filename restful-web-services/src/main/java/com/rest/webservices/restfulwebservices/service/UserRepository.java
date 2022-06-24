package com.rest.webservices.restfulwebservices.service;

import org.springframework.data.repository.CrudRepository;

import com.rest.webservices.restfulwebservices.bean.User;

public interface UserRepository extends CrudRepository<User, Integer>{


}
