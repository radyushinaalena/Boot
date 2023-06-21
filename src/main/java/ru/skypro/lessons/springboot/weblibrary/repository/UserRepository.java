package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.weblibrary.pojo.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}