package com.example.novelservice.repository;

import com.example.novelservice.models.Chapter;
import com.example.novelservice.models.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelRepository extends JpaRepository<Novel,Long> {

}
