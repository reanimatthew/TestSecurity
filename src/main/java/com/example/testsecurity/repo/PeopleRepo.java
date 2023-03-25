package com.example.testsecurity.repo;

import com.example.testsecurity.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepo extends JpaRepository<People, Long> {
    Optional<People> findByPassport(String passport);
    Optional<People> findByLogin(String login);
}
