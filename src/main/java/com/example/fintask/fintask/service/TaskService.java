package com.example.fintask.fintask.service;

import com.example.fintask.fintask.model.Task;
import com.example.fintask.fintask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        task.setCreatorId(currentUser);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findByCreatorId(currentUser);
    }

    public Optional<Task> getTaskById(Long id) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent() && task.get().getCreatorId().equals(currentUser)) {
            return task;
        }
        return Optional.empty();
    }

    public Task updateTask(Task task) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!task.getCreatorId().equals(currentUser)) {
            throw new RuntimeException("You are not allowed to update this task");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent() && task.get().getCreatorId().equals(currentUser)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("You are not allowed to delete this task");
        }
    }

    public List<Task> getTasksByStatus(String status) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findByStatusAndCreatorId(status, currentUser);
    }

    public Task updateTaskStatus(Long id, String status) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (!task.getCreatorId().equals(currentUser)) {
                throw new RuntimeException("You are not allowed to update this task");
            }
            task.setStatus(status);
            return taskRepository.save(task);
        }
        // TODO: write more specific exceptions
        throw new TaskNotFoundException("Task not found with id " + id);
    }
}
