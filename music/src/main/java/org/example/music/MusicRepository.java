package org.example.music;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {

    @SuppressWarnings("null")
    @Override
    Optional<Music> findById(Long id);

    Music findByName(String name);
}
