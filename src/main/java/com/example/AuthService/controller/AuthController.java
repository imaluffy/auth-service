package com.example.AuthService.controller;


import com.example.AuthService.model.UserCred;
import com.example.AuthService.service.AuthService;
import com.example.AuthService.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;
    @Autowired
    private com.example.AuthService.service.myUserDetailsService myUserDetailsService;

    @Autowired
    JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserCred userCred){
        UserCred response= authService.addUser(userCred);

        if(response!=null){
           return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
    }


    @PostMapping("/login")
    public String getUser(@RequestBody UserCred userCred){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCred.getUserId(),userCred.getUserPass())
        );
//        Optional<UserCred> response= authService.getUser(userCred);

        UserDetails userDetails=myUserDetailsService.loadUserByUsername(userCred.getUserId());
        return jwtService.getToken(userDetails);

//        if(authentication.isAuthenticated()){
//            return ResponseEntity.status(HttpStatus.FOUND).body("User Found");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");


    }

    @GetMapping("/hey")
    public ResponseEntity<String> sayHello(){

        //if token is validated, then response entity, else if the token is no validated, i am throeing error,
        //now i want to tell to throw tis o tell
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hey how's you");

    }
}
