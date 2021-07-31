package com.songs.playlist.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.songs.playlist.model.PlayList;
import com.songs.playlist.model.Song;
import com.songs.playlist.repository.PlayListRepository;
import com.songs.playlist.repository.SongRepository;

@Controller
public class UIController {

	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	SongRepository songRepository;

	@GetMapping("/")
	public String showUserList(Model model) {
		model.addAttribute("playList", playListRepository.findAll());
		return "index";
	}

	@GetMapping("/removeSongFromPlayList")
	public String deleteSong(Model model, @RequestParam("songId") int songId,@RequestParam("playListId") int playListId) {
		Optional<PlayList> playList = playListRepository.findById(playListId);
		if (playList.isPresent()) {
			Optional<Song> song = songRepository.findById(songId);
			if (song.isPresent()) {
				playList.get().getSong().remove(song.get());
				playListRepository.save(playList.get());
			}
		}
		model.addAttribute("playList", playListRepository.findAll());
		return "index";
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

	@GetMapping("/deletePlayList/{id}")
	public String deletePlayList(Model model, @PathVariable("id") int id) {
		playListRepository.deleteById(id);
		model.addAttribute("playList", playListRepository.findAll());
		return "index";
	}
}
