package org.example.music;

public class MusicDTO {
    private Long id;
    private String name;
    private String actor;
    private String description;
    public String getActor() {
        return actor;
    }
    public String getDescription() {
        return description;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public MusicDTO(Music music) {
        this.id = music.getId();
        this.name = music.getName();
        this.actor = music.getActor();
        this.description = music.getDescription();
    }
}