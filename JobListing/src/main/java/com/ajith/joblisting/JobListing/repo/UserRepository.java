package com.ajith.joblisting.JobListing.repo;

import com.ajith.joblisting.JobListing.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {


}

