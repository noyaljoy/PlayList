package com.songs.playlist.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Song")
public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 164039146208639402L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "singer")
	private String singer;

	@JsonIgnore
	@ManyToMany(mappedBy = "song", fetch = FetchType.LAZY)
    private Set<PlayList> playList = new HashSet<>();
	
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(String name, String singer, Set<PlayList> playList) {
		super();
		this.name = name;
		this.singer = singer;
		this.playList = playList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Song(String name, String singer) {
		super();
		this.name = name;
		this.singer = singer;
	}

	public Set<PlayList> getPlayList() {
		return playList;
	}

	public void setPlayList(Set<PlayList> playList) {
		this.playList = playList;
	}

	

}
