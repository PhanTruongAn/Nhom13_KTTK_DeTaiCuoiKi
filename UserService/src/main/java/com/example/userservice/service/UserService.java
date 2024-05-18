package com.example.userservice.service;

import com.example.userservice.models.User;
import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;

    public User login(String userName){
        return repository.findByUserName(userName);
    }
    public User register(User user){
        return repository.save(user);
    }
}
