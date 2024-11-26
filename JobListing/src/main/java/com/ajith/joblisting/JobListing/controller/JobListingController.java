package com.ajith.joblisting.JobListing.controller;


import com.ajith.joblisting.JobListing.model.User;
import com.ajith.joblisting.JobListing.model.UserResponse;
import com.ajith.joblisting.JobListing.repo.JobRepository;
import com.ajith.joblisting.JobListing.service.JWTService;
import com.ajith.joblisting.JobListing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class JobListingController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> register(@RequestBody User user){
        User savedUser = userService.addUser(user);
        String generateToken = jwtService.generateToken(savedUser.getUsername());
        UserResponse userResponse = new UserResponse(savedUser,generateToken);
        kafkaTemplate.send("medium-events", user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/view-users")
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = userService.viewUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
