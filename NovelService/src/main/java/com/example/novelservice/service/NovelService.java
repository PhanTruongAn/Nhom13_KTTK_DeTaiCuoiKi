package com.example.novelservice.service;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelService {
    @Autowired
    private NovelRepository repository;

    public Novel saveNovel(Novel novel){
        return repository.save(novel);
    }
    public Novel updateNovel(Novel novel){
        return repository.save(novel);
    }
    public void deleteNovelById(long id){
        repository.deleteById(id);
    }
    public List<Novel> getAllNovel(){
        return repository.findAll();
    }
}
