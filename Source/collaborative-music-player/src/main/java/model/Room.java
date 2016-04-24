package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="room")
public class Room {
	private int id;
	private String name;
	private long dateCreatedInMillisecondsSinceEpoch;
	private int hostId;
	private List<User> currentRoomUsers;
	
	public Room(){};
	
	public Room(String name, long dateCreated, int hostId)
	{
		this.name = name;
		dateCreatedInMillisecondsSinceEpoch = dateCreated;
		this.hostId = hostId;
	}
	@Id	
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="room_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="room_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="date_created")
	public long getDateCreatedInMillisecondsSinceEpoch() {
		return dateCreatedInMillisecondsSinceEpoch;
	}
	public void setDateCreatedInMillisecondsSinceEpoch(long dateCreatedInMillisecondsSinceEpoch) {
		this.dateCreatedInMillisecondsSinceEpoch = dateCreatedInMillisecondsSinceEpoch;
	}
	@Column(name="host_user_id")
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currentRoom")
	public List<User> getCurrentRoomUsers() {
		return currentRoomUsers;
	}

	public void setCurrentRoomUsers(List<User> currentRoomUsers) {
		this.currentRoomUsers = currentRoomUsers;
	}
}
