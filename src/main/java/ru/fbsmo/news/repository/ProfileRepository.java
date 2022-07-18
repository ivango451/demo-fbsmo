package ru.fbsmo.news.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fbsmo.news.entity.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByProfileId(Long id);


    Optional<Profile> findByProfileId(String username);
}
