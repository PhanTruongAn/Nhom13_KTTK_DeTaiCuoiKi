package com.example.novelservice.service;

import com.example.novelservice.models.Novel;
import com.example.novelservice.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
