package com.ajith.joblisting.JobListing.repo;

import com.ajith.joblisting.JobListing.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    List<User> findByUsername(String username);
}

