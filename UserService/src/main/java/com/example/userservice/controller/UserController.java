package com.example.userservice.controller;

import com.example.userservice.config.Jwt;
import com.example.userservice.dto.LoginToken;
import com.example.userservice.models.Notification;
import com.example.userservice.models.Novel;
import com.example.userservice.models.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User saveUser = service.register(user);
        return ResponseEntity.ok(saveUser);
    }
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        User myUser = service.login(user.getUserName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (myUser == null || !bCryptPasswordEncoder.matches(user.getPassword(), myUser.getPassword()))
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        String token = Jwt.generateToken(user);
        LoginToken loginToken = new LoginToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + loginToken.getToken());
        return ResponseEntity.ok().headers(headers).body(myUser);
    }
    @GetMapping("/get-all-novel")
    public ResponseEntity<List<Novel>> getAllNovel() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/novel/get-all";
        // Sử dụng restTemplate.exchange với phương thức ngắn hơn
        List<Novel> novels = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Novel>>() {}
        ).getBody();
        // Trả về danh sách dưới dạng JSON
        return ResponseEntity.ok(novels);
    }
    @PostMapping("/update-novels/{id}")
    public ResponseEntity<?> getNovel(@PathVariable Long id,@RequestHeader("user-id") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/novel/find-by-id/" + id;
        // Gọi REST API để lấy danh sách các chương dưới dạng JSON
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonResponse = responseEntity.getBody();
        // Khởi tạo danh sách các novels
        Optional<User> op = repository.findById(userId);
        // Chuyển đổi JSON thành một cây JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            // Trích xuất tên của novel từ đối tượng JSON
            String novelName = jsonNode.get("novelName").asText();
            if (op.isPresent()) {
                User user = op.get();
                List<String> novels = user.getNovels();
                if (!novels.contains(novelName)) {
                    novels.add(novelName);
                    user.setNovels(novels);
                    repository.save(user);
                }else{
                    return ResponseEntity.badRequest().body("Tiểu thuyết tồn tại trong danh sách");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(op);
    }

    @GetMapping("/get-notifications/{id}")
    public ResponseEntity<List<Notification>> getAllNotification(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8085/notification/find-by-userId/"+id;
        List<Notification> notifications = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Notification>>() {}
        ).getBody();
        // Trả về danh sách dưới dạng JSON
        return ResponseEntity.ok(notifications);
    }
}
