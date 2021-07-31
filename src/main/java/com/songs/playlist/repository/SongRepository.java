package com.songs.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.songs.playlist.model.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {

}