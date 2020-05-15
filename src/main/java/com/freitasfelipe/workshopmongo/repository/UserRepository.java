package com.freitasfelipe.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.freitasfelipe.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
