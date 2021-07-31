package com.songs.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.songs.playlist.model.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {

}