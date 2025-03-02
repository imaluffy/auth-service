package com.example.AuthService.controller;


import com.example.AuthService.model.UserCred;
import com.example.AuthService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserCred userCred){
        UserCred response= authService.addUser(userCred);

        if(response!=null){
           return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
    }


    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody UserCred userCred){
        Optional<UserCred> response= authService.getUser(userCred);

        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    @GetMapping("/hey")
    public String sayHello(){
        return "Hello, Konichiwa guys";
    }
}
