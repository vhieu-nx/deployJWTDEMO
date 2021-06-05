package com.example.be.service;

import com.example.be.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISongService {
    Optional<Song> findById(Long id);
    Song save(Song song);
    Page<Song> findAll(Pageable pageable);
    void deleteById(Long id);
}
