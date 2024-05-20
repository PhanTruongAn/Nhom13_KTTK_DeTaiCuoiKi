package com.example.novelservice.controller;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import com.example.novelservice.service.NovelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
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

    @GetMapping("/update-all-chapter/{id}")
    public ResponseEntity<?> updateChapters(@PathVariable Long id) {
       return service.updateChapters(id);
    }
    @GetMapping("/update-all-comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id) {
        return service.updateComment(id);
    }
    @GetMapping("/get-chapter/{id}")
    public ResponseEntity<?> getChapter(@PathVariable Long id){
        return service.getChapter(id);
    }

}
