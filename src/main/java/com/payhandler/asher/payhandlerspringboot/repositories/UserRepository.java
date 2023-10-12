package com.payhandler.asher.payhandlerspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payhandler.asher.payhandlerspringboot.domain.user.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocument(String document);

    Optional<User> findById(Long id);
}
