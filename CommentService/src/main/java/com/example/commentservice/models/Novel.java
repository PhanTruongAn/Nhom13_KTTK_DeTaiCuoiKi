package com.example.commentservice.models;

import com.example.commentservice.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "novels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String novelName;
    private String description;
    private String author;
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "novel_id"))
    @Column(name = "genre", length = 255, nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Genre> genre;
    @OneToMany(mappedBy = "novel",cascade = CascadeType.ALL)
    private List<Chapter> chapters;
    @OneToMany(mappedBy = "novel",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
