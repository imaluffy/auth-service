package com.example.AuthService.repo;

import com.example.AuthService.model.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<UserCred, String> {
}
