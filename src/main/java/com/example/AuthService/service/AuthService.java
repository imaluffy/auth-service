package com.example.AuthService.service;


import com.example.AuthService.model.UserCred;
import com.example.AuthService.repo.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
   AuthRepo authRepo;

    public UserCred addUser(UserCred userCred) {
        return authRepo.save(userCred);
    }

    public Optional<UserCred> getUser(UserCred userCred) {
        return authRepo.findById(userCred.getUserId());
    }
}
