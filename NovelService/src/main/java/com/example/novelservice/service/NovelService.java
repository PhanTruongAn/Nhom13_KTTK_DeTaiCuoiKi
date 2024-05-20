package com.example.novelservice.service;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NovelService {
    @Autowired
    private NovelRepository repository;

    public Novel saveNovel(Novel novel){
        return repository.save(novel);
    }
    public boolean updateNovel(Novel novel){
        Optional<Novel> op = repository.findById(novel.getId());
         if (op.isPresent()){
             repository.save(novel);
             return true;
         }
        return false;
    }
    public boolean deleteNovelById(long id){
        repository.deleteById(id);
        return true;
    }
    public List<Novel> getAllNovel(){
        return repository.findAll();
    }
    @Retryable(maxAttempts=3, value = RuntimeException.class,
            backoff = @Backoff(delay = 3000, multiplier = 1))
    public ResponseEntity<?> updateComment(@PathVariable Long id) {
        System.out.println("Retry Run");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/comment/comments-by-novel/" + id;
        // Gọi REST API để lấy danh sách các chương dưới dạng JSON
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonResponse = responseEntity.getBody();

        // Khởi tạo danh sách các titles
        List<String> listComment = new ArrayList<>();
        // Chuyển đổi JSON thành một cây JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Duyệt qua mỗi nút trong cây JSON và lấy giá trị của trường "title"
            for (JsonNode commentNode : jsonNode) {
                String text = commentNode.get("text").asText();
                listComment.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Khởi tạo một đối tượng Novel và gán danh sách titles vào trường chapters của nó
        Novel op = repository.findById(id).get();
        op.setComments(listComment);
            repository.save(op);
        return ResponseEntity.status(HttpStatus.OK).body(op);
    }
    @Recover
    public ResponseEntity recover() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve data.");
    }


    @Retryable(maxAttempts=3, value = RuntimeException.class,
            backoff = @Backoff(delay = 3000, multiplier = 1))
    public ResponseEntity<?> updateChapters(@PathVariable Long id) {
        System.out.println("Retry Run");
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
        Novel op = repository.findById(id).get();
            // Cập nhật trường chapters của đối tượng Novel với danh sách tiêu đề chương mới
        op.setChapters(chapterTitles);
            // Lưu đối tượng Novel đã cập nhật vào cơ sở dữ liệu
        repository.save(op);
        return ResponseEntity.status(HttpStatus.OK).body(op);
    }




    @Retryable(maxAttempts=3, value = RuntimeException.class,
            backoff = @Backoff(delay = 3000, multiplier = 1))
    public ResponseEntity<?> getChapter(@PathVariable Long id) {
        System.out.println("Retry Run");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/chapter/find-by-id/"+id;
        String response = restTemplate.getForObject(url,String.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
