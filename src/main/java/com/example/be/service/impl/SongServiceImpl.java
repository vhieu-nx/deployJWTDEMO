package com.example.be.service.impl;

import com.example.be.model.Song;
import com.example.be.model.User;
import com.example.be.repository.ISongRepository;
import com.example.be.security.userprincal.UserDetailServiceImpl;
import com.example.be.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    ISongRepository songRepository;
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song save(Song song) {
        User user = userDetailService.getCurrentUser();
        song.setUser(user);
        return songRepository.save(song);
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {

    }
}
