package com.example.fintask.fintask;

import com.example.fintask.fintask.model.AppUser;
import com.example.fintask.fintask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Initializer {

    @Bean
    CommandLineRunner Initializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // init user for simplicity first ::))
            if (userRepository.count() == 0) {
                AppUser user = new AppUser("user1", passwordEncoder.encode("password"));
                userRepository.save(user);
                AppUser user2 = new AppUser("user2", passwordEncoder.encode("password"));
                userRepository.save(user2);
                System.out.println("Default user created: user1 / password");
            }
        };
    }
}