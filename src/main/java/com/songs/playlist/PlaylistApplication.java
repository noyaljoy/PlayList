package com.songs.playlist;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.songs.playlist.model.PlayList;
import com.songs.playlist.model.Song;
import com.songs.playlist.repository.PlayListRepository;
import com.songs.playlist.repository.SongRepository;

@SpringBootApplication
public class PlaylistApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistApplication.class, args);
	}
	 @Bean
	    public CommandLineRunner mappingDemo(PlayListRepository playListRepository,
	                                         SongRepository songsRepository) {
	        return args -> {

	        	PlayList playList = new PlayList("Melody");
	        	PlayList playList2 = new PlayList("Favourites");

	        	playListRepository.save(playList);
	        	playListRepository.save(playList2);

	        	Song song1 = new Song("IntroSong", "Kester");
	        	Song song2 = new Song("WelcomeSsong", "Chitra");
	        	Song song3 = new Song("MiddleSong", "Michel");
	        	
	        	songsRepository.saveAll(Arrays.asList(song1, song2, song3));

	        	playList.getSong().addAll(Arrays.asList(song1, song2, song3));
	        	playList2.getSong().addAll(Arrays.asList(song3));

	            playListRepository.save(playList);
	            playListRepository.save(playList2);

	        };
	    }
}
