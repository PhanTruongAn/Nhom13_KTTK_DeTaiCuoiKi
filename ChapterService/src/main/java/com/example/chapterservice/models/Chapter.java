package com.example.chapterservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("chapters")
@Data
public class Chapter {
    @Id
    private Long id;
    private Long novelId;
    private String title;
    private String content;

}
