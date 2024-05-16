package com.example.chapterservice.service;

import com.example.chapterservice.models.Chapter;
import com.example.chapterservice.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    private static final String KEY_PREFIX = "chapter:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChapterRepository repository;

    public void create(Chapter chapter) {
        String key = KEY_PREFIX + chapter.getId();
        redisTemplate.opsForValue().set(key, chapter);
    }

    public boolean existsById(Long id) {
        String key = KEY_PREFIX + id;
        return redisTemplate.hasKey(key);
    }
    public Chapter findById(Long id) {
        String key = KEY_PREFIX + id;
        return (Chapter) redisTemplate.opsForValue().get(key);
    }
    public void deleteById(Long id) {
        String key = KEY_PREFIX + id;
        redisTemplate.delete(key);
    }



    public void update(Chapter chapter) {
        String key = KEY_PREFIX + chapter.getId();
        redisTemplate.opsForValue().set(key, chapter);
    }
}
