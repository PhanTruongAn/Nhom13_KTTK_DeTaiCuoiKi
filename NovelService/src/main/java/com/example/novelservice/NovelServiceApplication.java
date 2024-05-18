package com.example.novelservice;

import com.example.novelservice.enums.Genre;
import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.*;
import com.example.novelservice.service.NovelService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class NovelServiceApplication {
@Autowired
public NovelService service;
    @Autowired
    public NovelRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(NovelServiceApplication.class, args);
    }

//   @Bean
CommandLineRunner initDatabase(NovelRepository novelRepository) {
    return args -> {
        Faker faker = new Faker();

        // Tạo 4 User


        // Tạo 10 Novel


    };
}

}
