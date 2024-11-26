package com.ajith.joblisting.JobListing.repo;

import com.ajith.joblisting.JobListing.model.UserInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<UserInformation,Integer> {


}
