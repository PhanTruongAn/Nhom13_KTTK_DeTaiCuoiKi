package com.example.userservice.controller;

import com.example.userservice.models.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public User login(String username,String password){
        System.out.println("username:"+ username + "password:" + password);
        return service.login(username,password);
    }
}
