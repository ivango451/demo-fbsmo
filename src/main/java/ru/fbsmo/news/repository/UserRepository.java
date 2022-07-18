package ru.fbsmo.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fbsmo.news.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
