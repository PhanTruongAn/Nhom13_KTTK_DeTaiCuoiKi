package com.example.chapterservice.repository;

import com.example.chapterservice.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long> {
}
