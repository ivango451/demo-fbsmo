package ru.fbsmo.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fbsmo.news.entity.Tag;

import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
}
