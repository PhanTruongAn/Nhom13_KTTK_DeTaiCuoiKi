package com.example.novelservice.controller;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import com.example.novelservice.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
