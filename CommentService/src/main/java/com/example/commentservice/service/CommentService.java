package com.example.commentservice.service;

import com.example.commentservice.models.Comment;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public Comment saveComment(Comment comment){
        return repository.save(comment);
    }
    public boolean updateComment(Comment comment){
        Optional<Comment> op = repository.findById(comment.getId());
        if (op.isPresent()){
            repository.save(comment);
            return true;
        }
        return false;
    }
    public boolean deleteCommentById(long id){
        repository.deleteById(id);
        return true;
    }
    public List<Comment> getAllComment(){
        return repository.findAll();
    }
}
