package ru.skypro.lessons.springboot.weblibrary.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String role;
}