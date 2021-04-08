package ru.gladyshev.demo.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gladyshev.demo.twitter.entity.Role;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
