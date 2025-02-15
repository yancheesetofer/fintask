package com.example.fintask.fintask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String creatorId;

    public Task(String title, String description, String status, String creatorId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
    }
    
    // default constructor for JPA initiation
    public Task() {
    }
}