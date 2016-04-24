package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="song")
public class Song {
	private int id;
	private int submitterUserId;
	private String name;
	private String link;
	private int duration;
	private int roomId;
	
	public Song(){}
	
	public Song(int submitterUserId, int roomId, String name, String link, int duration)
	{
		this.submitterUserId = submitterUserId;
		this.roomId = roomId;
		this.name = name;
		this.link = link;
		this.duration = duration;
	}
	@Id	
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="song_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="user_id")
	public int getSubmitterUserId() {
		return submitterUserId;
	}

	public void setSubmitterUserId(int submitterUserId) {
		this.submitterUserId = submitterUserId;
	}
	@Column(name="song_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	@Column(name="duration")
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Column(name="room_history_id")
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
}
