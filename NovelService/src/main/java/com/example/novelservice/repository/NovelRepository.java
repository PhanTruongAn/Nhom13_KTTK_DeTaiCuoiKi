package com.example.novelservice.repository;

import com.example.novelservice.models.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends JpaRepository<Novel,Long> {
}
