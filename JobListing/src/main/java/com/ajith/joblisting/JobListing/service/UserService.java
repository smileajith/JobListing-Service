package com.ajith.joblisting.JobListing.service;

import com.ajith.joblisting.JobListing.model.User;
import com.ajith.joblisting.JobListing.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User addUser(User user) {

        return userRepository.save(user);
    }

    public List<User> viewUsers(){

        return userRepository.findAll();
    }


}
