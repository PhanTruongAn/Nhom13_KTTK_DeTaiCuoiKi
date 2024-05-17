package com.example.chapterservice.controller;

import com.example.chapterservice.models.Chapter;
import com.example.chapterservice.models.Novel;
import com.example.chapterservice.repository.ChapterRepository;
import com.example.chapterservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService service;
    @Autowired
    private ChapterRepository repository;

    @PostMapping(value = "/create-chapter")
    public ResponseEntity<String> createChapter(@RequestBody Chapter chapter, @RequestHeader("novel-id") Long novelId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/novel/find-by-id/"+novelId;
        ResponseEntity<Novel> responseEntity = restTemplate.getForEntity(url, Novel.class);
        Novel novel = responseEntity.getBody();
        chapter.setNovel(novel);
        repository.save(chapter);
        service.create(chapter);
        return ResponseEntity.status(HttpStatus.OK).body("Create chapter is success!");
    }

    @GetMapping("/get-chapter-by-novel/{id}")
    public ResponseEntity<List<Chapter>> getChapters(@PathVariable Long id){
         RestTemplate restTemplate = new RestTemplate();
         String url = "http://localhost:8083/novel/get-chapters/" + id;
        ResponseEntity<List<Chapter>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Chapter>>() {}
        );
        List<Chapter> chapters = response.getBody();
        service.saveChapters(chapters);
        return response;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Chapter> findById(@PathVariable("id") Long id) {
        if (service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete chapter is success!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chapter not found!");
        }
    }

    @PutMapping("/update-by-id")
    public ResponseEntity<String> updateById(@RequestBody Chapter chapter) {
        if (service.existsById(chapter.getId())) {
            service.update(chapter);
            return ResponseEntity.status(HttpStatus.OK).body("Update chapter is success!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chapter not found!");
        }
    }

}
