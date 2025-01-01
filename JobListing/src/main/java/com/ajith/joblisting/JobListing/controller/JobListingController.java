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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
        //kafkaTemplate.send("medium-events", user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/view-users")
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = userService.viewUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/get-users")
    public ResponseEntity<?> getUserDetails(@RequestParam(value = "username", required = false) String username) {
        if(username != null && !username.isEmpty()){
            List<User> user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } else {
            List<User> user2 = userService.viewUsers();
            return new ResponseEntity<>(user2,HttpStatus.OK);
        }

    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> updateUserEmail(@PathVariable String username , @RequestBody Map<String,String> updates){

        List<User> user = userService.getUserByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
         User user1 = user.get(0);
        if(updates.containsKey("email")){
            user1.setEmail(updates.get("email"));
        }
        userService.addUser(user1);
        return new ResponseEntity<>(user1,HttpStatus.CREATED);
    }

}
