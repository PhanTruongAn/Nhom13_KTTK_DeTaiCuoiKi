package com.example.userservice.service;

import com.example.userservice.models.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User login(String userName,String password){
        return repository.findByUserNameAndPassword(userName,password);
    }
}
