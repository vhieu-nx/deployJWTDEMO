package com.example.be.controller;

import com.example.be.dto.reponse.ReponseMessage;
import com.example.be.model.Song;
import com.example.be.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    SongServiceImpl songService;
    @GetMapping()
    public ResponseEntity<?> pageSong(@PageableDefault(sort = "nameSong",direction = Sort.Direction.ASC)Pageable pageable){
        Page<Song> songPage = songService.findAll(pageable);
        if(songPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage,HttpStatus.OK);

    }
    @PostMapping()
    public ResponseEntity<?> createSong(@Valid @RequestBody Song song){
        songService.save(song);
        return new ResponseEntity<>(new ReponseMessage("yes"), HttpStatus.OK);
    }
}
