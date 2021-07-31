package com.songs.playlist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.playlist.model.Song;
import com.songs.playlist.repository.SongRepository;

@RestController
@RequestMapping("/songApi")
public class SongController {

	@Autowired
	SongRepository songRepository;

	@GetMapping("/song")
	public ResponseEntity<List<Song>> getAllSong() {
		try {
			List<Song> song = new ArrayList<Song>();
			song = songRepository.findAll();
			if (song.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(song, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/song/{id}")
	public ResponseEntity<Song> getSongById(@PathVariable("id") int id) {
		Optional<Song> song = songRepository.findById(id);
		if (song.isPresent()) {
			return new ResponseEntity<>(song.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/song")
	public ResponseEntity<Song> createSong(@RequestBody Song song ) {
		try {
			song = songRepository.save(new Song(song.getName(),song.getSinger()));
			return new ResponseEntity<>(song, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/song/{id}")
	public ResponseEntity<HttpStatus> deleteSong(@PathVariable("id") int id) {
		try {
			songRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/song")
	public ResponseEntity<HttpStatus> deleteAllSong() {
		try {
			songRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
