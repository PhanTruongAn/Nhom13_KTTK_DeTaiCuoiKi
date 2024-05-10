package com.example.chapterservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "chapters")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int numberText;
    @Lob
    private String content;
    @ManyToOne
    private Novel novel;

}
