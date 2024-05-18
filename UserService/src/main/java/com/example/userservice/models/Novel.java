package com.example.userservice.models;

import java.util.List;
import java.util.Set;

public class Novel {
    private Long id;
    private String novelName;
    private String description;
    private String author;
    private Set<String> genre;
    private List<String> chapters;
    private List<String> comments;

    // Constructors
    public Novel() {}

    public Novel(Long id, String novelName, String description, String author, Set<String> genre, List<String> chapters, List<String> comments) {
        this.id = id;
        this.novelName = novelName;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.chapters = chapters;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }

    public List<String> getChapters() {
        return chapters;
    }

    public void setChapters(List<String> chapters) {
        this.chapters = chapters;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}

