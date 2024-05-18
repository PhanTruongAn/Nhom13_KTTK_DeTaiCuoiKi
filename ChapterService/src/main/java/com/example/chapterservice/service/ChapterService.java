package com.example.chapterservice.service;

import com.example.chapterservice.models.Chapter;
import com.example.chapterservice.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ChapterService {
    private static final String KEY_PREFIX = "chapters:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChapterRepository chapterRepository;


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
    public void saveChapters(List<Chapter> chapters) {
        for (Chapter chapter : chapters) {
            String key = KEY_PREFIX + chapter.getId();
            redisTemplate.opsForValue().set(key, chapter);
        }
    }


    public void update(Chapter chapter) {
        String key = KEY_PREFIX + chapter.getId();
        redisTemplate.opsForValue().set(key, chapter);
    }
    public List<Chapter> getChaptersByNovelId(Long novelId) {
        List<Chapter> chapters = new ArrayList<>();

        // Tạo key pattern dựa trên novelId
        String keyPattern = KEY_PREFIX + "*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        // Lặp qua các keys và lấy các chương có novelId trùng với novelId đã cho
        for (String key : keys) {
            Chapter chapter = (Chapter) redisTemplate.opsForValue().get(key);
            if (chapter != null && chapter.getNovelId().equals(novelId)) {
                chapters.add(chapter);
            }
        }

        return chapters;
    }
}
