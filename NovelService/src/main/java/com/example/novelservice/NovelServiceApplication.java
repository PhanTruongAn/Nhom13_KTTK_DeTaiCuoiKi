package com.example.novelservice;

import com.example.novelservice.enums.Genre;
import com.example.novelservice.models.Chapter;
import com.example.novelservice.models.Comment;
import com.example.novelservice.models.Novel;
import com.example.novelservice.models.User;
import com.example.novelservice.repository.*;
import com.example.novelservice.service.NovelService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class NovelServiceApplication {
@Autowired
public NovelService service;
    public static void main(String[] args) {
        SpringApplication.run(NovelServiceApplication.class, args);
    }

//   @Bean
CommandLineRunner initDatabase(NovelRepository novelRepository, UserRepository userRepository) {
    return args -> {
        Faker faker = new Faker();

        // Tạo 4 User
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setUserName(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            users.add(user);
            userRepository.save(user);
        }

        // Tạo 10 Novel
        for (int i = 0; i < 10; i++) {
            createNovel(faker, novelRepository, users);
        }
    };
}

private void createNovel(Faker faker, NovelRepository novelRepository, List<User> users) {
    // Tạo Novel mới
    Novel novel = new Novel();
    novel.setNovelName(faker.book().title());
    novel.setDescription(faker.lorem().paragraph());
    novel.setAuthor(faker.name().fullName());

    // Tạo các Genre ngẫu nhiên
    Set<Genre> genres = new HashSet<>();
    genres.add(faker.options().option(Genre.values()));
    genres.add(faker.options().option(Genre.values()));
    novel.setGenre(genres);

    // Lưu lại Novel
    novelRepository.save(novel);
}



}
