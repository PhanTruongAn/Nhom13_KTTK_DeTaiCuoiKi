package com.example.novelservice.controller;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import com.example.novelservice.service.NovelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/novel")
public class NovelController {
    @Autowired
    private NovelService service;
    @Autowired
    private NovelRepository repository;
    @GetMapping("/get-all")
    public List<Novel> getAllNovel(){
        return service.getAllNovel();
    }
    @GetMapping("/find-by-id/{id}")
    public Optional<Novel> getNovelByID(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/delete-by-id/{id}")
    public ResponseEntity<Boolean> deleteNovel(@PathVariable Long id ){
        return ResponseEntity.ok(service.deleteNovelById(id));
    }

    @PostMapping("/create-novel")
    public Novel createNovel(@RequestBody Novel novel){
        return service.saveNovel(novel);
    }
    @PutMapping("/update-novel")
    public ResponseEntity<Boolean> updateNovel(@RequestBody Novel novel){
        return ResponseEntity.ok(service.updateNovel(novel));
    }

    @PostMapping("/update-all-chapter/{id}")
    public Optional<Novel> getChapters(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/chapter/chapters-by-novel/" + id;
        // Gọi REST API để lấy danh sách các chương dưới dạng JSON
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonResponse = responseEntity.getBody();

        // Khởi tạo danh sách các titles
        List<String> chapterTitles = new ArrayList<>();

        // Chuyển đổi JSON thành một cây JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Duyệt qua mỗi nút trong cây JSON và lấy giá trị của trường "title"
            for (JsonNode chapterNode : jsonNode) {
                String title = chapterNode.get("title").asText();
                chapterTitles.add(title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Khởi tạo một đối tượng Novel và gán danh sách titles vào trường chapters của nó
        Optional<Novel> op = repository.findById(id);
        if (op.isPresent()) {
            Novel novel = op.get();
            // Cập nhật trường chapters của đối tượng Novel với danh sách tiêu đề chương mới
            novel.setChapters(chapterTitles);
            // Lưu đối tượng Novel đã cập nhật vào cơ sở dữ liệu
            service.saveNovel(novel);
        }
        return op;
    }
}
