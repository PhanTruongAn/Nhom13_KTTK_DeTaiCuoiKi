package com.example.notificationservice.service;

import com.example.notificationservice.models.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

    public Notification save(Notification notification){
        return repository.save(notification);
    }
    public boolean update(Notification notification){
        Optional<Notification> op = repository.findById(notification.getId());
        if (op.isPresent()){
            repository.save(notification);
            return true;
        }
        return false;
    }
    public boolean deleteById(long id){
        repository.deleteById(id);
        return true;
    }
    public List<Notification> getAll(){
        return repository.findAll();
    }
}
