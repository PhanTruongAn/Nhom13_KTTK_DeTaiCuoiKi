package com.example.notificationservice.controller;

import com.example.notificationservice.models.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationRepository repository;
    @Autowired
    private NotificationService service;

    @GetMapping("/get-all")
    public List<Notification> getAll(){
        return service.getAll();
    }
    @GetMapping("/find-by-id/{id}")
    public Optional<Notification> getByID(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/delete-by-id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id ){
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/find-by-userId/{id}")
    public List<Notification> findByUserId(@PathVariable Long id ){
        return repository.findByUserId(id);
    }
    @PostMapping("/create")
    public Notification create(@RequestBody Notification notification){
        return service.save(notification);
    }
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Notification notification){
        return ResponseEntity.ok(service.update(notification));
    }
}
