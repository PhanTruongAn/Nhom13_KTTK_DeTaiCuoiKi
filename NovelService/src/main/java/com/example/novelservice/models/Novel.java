package com.example.novelservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "novel")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String novelName;
    private String tacGia;
    private int soChuong;

    @Override
    public String toString() {
        return "Novel{" +
                "novelName='" + novelName + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", soChuong=" + soChuong +
                '}';
    }
}
