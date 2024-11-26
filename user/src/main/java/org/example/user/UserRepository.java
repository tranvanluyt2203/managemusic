package org.example.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    @SuppressWarnings("null")
    @Override
    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
