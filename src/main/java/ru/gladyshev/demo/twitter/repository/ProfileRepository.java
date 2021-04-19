package ru.gladyshev.demo.twitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gladyshev.demo.twitter.entity.Post;
import ru.gladyshev.demo.twitter.entity.Profile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByProfileId(Long id);


    Optional<Profile> findByProfileId(String username);
}
