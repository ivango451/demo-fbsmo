package ru.gladyshev.demo.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gladyshev.demo.twitter.entity.Tag;

import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
}
