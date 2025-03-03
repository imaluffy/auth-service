package com.example.AuthService.model;

import com.example.AuthService.repo.AuthRepo;
import com.example.AuthService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class myUserDetailsService implements UserDetailsService {

    @Autowired
    AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCred userCred= authRepo.findById(username).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );


        return new org.springframework.security.core.userdetails.User(
                userCred.getUserId(),
                userCred.getUserPass(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_"+userCred.getUserRole()))
        );

//          return new UserDetails() {
//              @Override
//              public Collection<? extends GrantedAuthority> getAuthorities() {
//                  return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+userCred.getUserRole()));
//              }
//
//              @Override
//              public String getPassword() {
//                  return userCred.getUserPass();
//              }
//
//              @Override
//              public String getUsername() {
//                  return userCred.getUserId();
//              }
//          };
    }
}
