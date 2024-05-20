package com.example.commentservice.controller;

import com.example.commentservice.models.Comment;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.service.CommentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private CommentService service;

    @GetMapping("/get-all")
    public List<Comment> getAll(){
        return service.getAllComment();
    }
    @GetMapping("/find-by-id/{id}")
    public Optional<Comment> getByID(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/delete-by-id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id ){
        return ResponseEntity.ok(service.deleteCommentById(id));
    }

    @PostMapping("/create-comment")
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "createCommentFallback")
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        return ResponseEntity.ok().body(service.saveComment(comment));
    }

    public ResponseEntity<?> createCommentFallback(Comment comment, Throwable t) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Bạn chỉ có thể comment 1 lần trong 30s.");
    }
    @PutMapping("/update-comment")
    public ResponseEntity<Boolean> update(@RequestBody Comment comment){
        return ResponseEntity.ok(service.updateComment(comment));
    }

    @GetMapping("/comments-by-novel/{id}")
    public List<Comment> getAllComment(@PathVariable Long id){
        List<Comment> list = repository.findByNovelId(id);
        return list;
    }
    @GetMapping("/comments-by-user/{id}")
    public List<Comment> getAllCommentByUser(@PathVariable Long id){
        List<Comment> list = repository.findByUserId(id);
        return list;
    }
}
