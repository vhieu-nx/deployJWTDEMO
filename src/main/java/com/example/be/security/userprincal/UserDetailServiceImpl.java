package com.example.be.security.userprincal;

import com.example.be.model.User;
import com.example.be.repository.IUserRepository;
import com.example.be.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with username or email"+username));
        return UserPrinciple.build(user);
    }
//    public User getCurrentUser(){
//        Optional<User> user;
//        String userName;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(principal instanceof UserDetails){
//            userName = ((UserDetails) principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        if (userRepository.existsByUsername(userName)) {
//            user = userService.findByUsername(userName);
//        } else {
//            user = Optional.of(new User());
//            user.get().setUsername("Anonymous");
//        }
//        return user.get();
//    }
public User getCurrentUser() {
    Optional<User> user;
    String userName;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
        userName = ((UserDetails) principal).getUsername();
    } else {
        userName = principal.toString();
    }
    if (userRepository.existsByUsername(userName)) {
        user = userService.findByUsername(userName);
    } else {
        user = Optional.of(new User());
        user.get().setUsername("Anonymous");
    }
    return user.get();
}
}
