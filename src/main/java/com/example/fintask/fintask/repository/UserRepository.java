package com.example.fintask.fintask.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fintask.fintask.model.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
