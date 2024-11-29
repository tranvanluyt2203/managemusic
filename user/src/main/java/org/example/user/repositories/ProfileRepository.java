package org.example.user.repositories;

import java.util.Optional;

import org.example.user.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    @SuppressWarnings("null")
    @Override
    Optional<Profile> findById(Long id);
}
