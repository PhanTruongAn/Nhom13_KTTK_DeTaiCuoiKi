package com.example.novelservice.controller;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import com.example.novelservice.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
