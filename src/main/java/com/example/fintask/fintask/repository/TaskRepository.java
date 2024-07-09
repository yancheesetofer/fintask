package com.example.fintask.fintask.repository;

import com.example.fintask.fintask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCreatorId(String creatorId);

    List<Task> findByStatus(String status);

}