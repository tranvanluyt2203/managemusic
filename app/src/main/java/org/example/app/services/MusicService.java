package org.example.app.services;

import org.example.app.ResponseApi;
import org.example.music.Music;
import org.example.music.MusicDTO;
import org.example.music.MusicRepository;
import org.example.security.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public ResponseApi AddNewMusic(Music music) {
        Music musicSave = musicRepository.save(music);

        return new ResponseApi(
                "success",
                "Add new music success",
                201,
                new MusicDTO(musicSave));
    }

    public ResponseApi getDetailMusic(Long id) {
        try {
            Music music = findById(id);
            return new ResponseApi(
                    "success",
                    "Get detail music success",
                    201,
                    new MusicDTO(music));
        } catch (Exception e) {
            return new ResponseApi(
                    "error",
                    e.getMessage(),
                    401);
        }

    }

    public Music findById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new NotFound("Profile with id " + id + " not found"));
    }

    public Music findByName(String name) {
        return musicRepository.findByName(name);
    }
}
