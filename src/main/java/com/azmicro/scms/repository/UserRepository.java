package com.azmicro.scms.repository;

import com.azmicro.scms.model.Role;
import com.azmicro.scms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);
    Optional<User> findByEmailAndPassword(String email, String password);
}
