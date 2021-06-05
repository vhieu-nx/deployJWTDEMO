package com.example.be.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameSong;
    @NotBlank
    private String avatarSong;
    @Lob
    @NotBlank
    private String lyrics;
    @NotBlank
    private String mp3Url;
@ManyToOne
User user;
    public Song() {
    }

    public Song(Long id, String nameSong, String avatarSong, String lyrics, String mp3Url, User user) {
        this.id = id;
        this.nameSong = nameSong;
        this.avatarSong = avatarSong;
        this.lyrics = lyrics;
        this.mp3Url = mp3Url;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getAvatarSong() {
        return avatarSong;
    }

    public void setAvatarSong(String avatarSong) {
        this.avatarSong = avatarSong;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
