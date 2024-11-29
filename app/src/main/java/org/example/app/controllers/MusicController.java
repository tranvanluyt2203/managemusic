package org.example.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.example.app.ResponseApi;
import org.example.app.services.MusicService;
import org.example.music.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/music")
public class MusicController {
    @Autowired
    private MusicService musicService;
    private static final List<String> AUDIO_MIME_TYPES = Arrays.asList("audio/mpeg", "audio/wav", "audio/ogg");
    private static final List<String> IMAGE_MIME_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @PostMapping("/add_music")
    public ResponseEntity<Object> addMusic(
            @RequestParam String name,
            @RequestParam String actor,
            @RequestParam String description,
            @RequestPart MultipartFile fileData,
            @RequestPart MultipartFile imageData) {
        try {
            if (name == null || name.isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Field Name is required",
                                400));
            }
            if (actor == null || actor.isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Field Actor is required",
                                400));
            }
            if (description != null && description.isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Field Description is not empty",
                                400));
            }
            if (!AUDIO_MIME_TYPES.contains(fileData.getContentType())) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Filedata just sound type",
                                400));
            }
            if (!IMAGE_MIME_TYPES.contains(imageData.getContentType())) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Imagdata just Image type",
                                400));
            }
            Music music = new Music(name, actor, description, fileData.getBytes(), imageData.getBytes());
            ResponseApi response = musicService.AddNewMusic(music);
            return ResponseEntity.status(response.getCode()).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseApi(
                            "error",
                            e.getMessage(),
                            500));
        }
    }

    @GetMapping("/get_detail")
    public ResponseEntity<Object> getDetailMusic(@RequestParam Long id) {
        if (id == null) {
            return ResponseEntity.status(400)
                    .body(new ResponseApi(
                            "error",
                            "Id is required",
                            400));
        }
        System.out.println("id==========" + id);
        ResponseApi response = musicService.getDetailMusic(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
