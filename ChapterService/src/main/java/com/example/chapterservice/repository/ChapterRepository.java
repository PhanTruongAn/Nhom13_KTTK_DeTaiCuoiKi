package com.example.chapterservice.repository;

import com.example.chapterservice.models.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Long> {
    List<Chapter> findByNovelId(Long novelId);
}
