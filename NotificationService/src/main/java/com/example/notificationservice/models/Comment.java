package com.example.notificationservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String text;
    private LocalDate timeComment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Novel novel;
}
