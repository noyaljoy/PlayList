package com.songs.playlist.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.songs.playlist.model.PlayList;
import com.songs.playlist.model.Song;
import com.songs.playlist.repository.PlayListRepository;
import com.songs.playlist.repository.SongRepository;

@RestController
@RequestMapping("/playListApi")
public class PlayListController {

	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	SongRepository songRepository;

	@GetMapping("/playList")
	public ResponseEntity<List<PlayList>> getAllPlayList() {
		try {
			List<PlayList> playList = playListRepository.findAll();
			if (playList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(playList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/playList/{id}")
	public ResponseEntity<PlayList> getPlayListById(@PathVariable("id") int id) {
		Optional<PlayList> playList = playListRepository.findById(id);
		if (playList.isPresent()) {
			return new ResponseEntity<>(playList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/playList")
	public ResponseEntity<PlayList> createPlayList(@RequestBody PlayList playList) {
		try {
			playList = playListRepository.save(new PlayList(playList.getName()));
			return new ResponseEntity<>(playList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addSongToPlayList")
	public ResponseEntity<List<PlayList>> addSongToPlayList(@RequestParam int playListId, @RequestParam int songId) {
		try {
			Optional<PlayList> playList = playListRepository.findById(playListId);
			if (playList.isPresent()) {
				Optional<Song> song = songRepository.findById(songId);
				if (song.isPresent()) {
					playList.get().getSong().addAll(Arrays.asList(song.get()));
					playListRepository.save(playList.get());
					List<PlayList> playLists = playListRepository.findAll();
					return new ResponseEntity<>(playLists, HttpStatus.CREATED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
    
	@DeleteMapping("/playList/{id}")
	public ResponseEntity<HttpStatus> deletePlayList(@PathVariable("id") int id) {
		try {
			playListRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/playList")
	public ResponseEntity<HttpStatus> deleteAllPlayList() {
		try {
			playListRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
