package com.project.angularjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.angularjava.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
	Optional<User> findById(String Id);
	//{'$or':[{ $where: '\"?0\" == \"\" '},{ 'userName' : {'$regex' : ?0, '$options' : 'i' }}]}
	//@Query(value="{[{fullname:{$regex:?0,$options:'i'}}]}")
	@Query("{ 'fullname':{$regex:?0,$options:'i'}}") 
	Page<User> findAll(String name, Pageable pageable);
}