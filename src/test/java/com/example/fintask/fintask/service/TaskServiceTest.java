package com.example.fintask.fintask.service;

import com.example.fintask.fintask.model.Task;
import com.example.fintask.fintask.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    // we use simple set up later
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user1");
    }

    @Test
    public void testCreateTask() {
        Task task = new Task("Title", "Description", "Pending", "user1");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask);
        assertEquals("user1", createdTask.getCreatorId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task("Title1", "Description1", "Pending", "user1");
        Task task2 = new Task("Title2", "Description2", "Completed", "user1");
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findByCreatorId("user1")).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getAllTasks();

        assertEquals(2, retrievedTasks.size());
        verify(taskRepository, times(1)).findByCreatorId("user1");
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task("Title", "Description", "Pending", "user1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> retrievedTask = taskService.getTaskById(1L);

        assertTrue(retrievedTask.isPresent());
        assertEquals("user1", retrievedTask.get().getCreatorId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task("Title", "Description", "Pending", "user1");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTask(task);

        assertNotNull(updatedTask);
        assertEquals("user1", updatedTask.getCreatorId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("Title", "Description", "Pending", "user1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetTasksByStatus() {
        Task task1 = new Task("Title1", "Description1", "Pending", "user1");
        Task task2 = new Task("Title2", "Description2", "Pending", "user1");
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskRepository.findByStatusAndCreatorId("Pending", "user1")).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getTasksByStatus("Pending");

        assertEquals(2, retrievedTasks.size());
        verify(taskRepository, times(1)).findByStatusAndCreatorId("Pending", "user1");
    }

    @Test
    public void testUpdateTaskStatus() {
        Task task = new Task("Title", "Description", "Pending", "user1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTaskStatus(1L, "Completed");

        assertNotNull(updatedTask);
        assertEquals("Completed", updatedTask.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    //TODO: Negative dan edge cases
}
