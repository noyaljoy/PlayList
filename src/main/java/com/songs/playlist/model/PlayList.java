package com.songs.playlist.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PlayList")
public class PlayList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045204105272814964L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "playList_song", 
		joinColumns = { @JoinColumn(name = "playList_id", referencedColumnName = "id", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "song_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Song> song = new HashSet<>();

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayList(String name) {
		super();
		this.name = name;
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

	public Set<Song> getSong() {
		return song;
	}

	public void setSong(Set<Song> song) {
		this.song = song;
	}

}
