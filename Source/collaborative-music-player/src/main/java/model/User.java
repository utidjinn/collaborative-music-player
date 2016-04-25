package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="app_user")

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean isPrivate;
	private Room currentRoom;
	private List<Room> recentlyJoinedRooms;
	
	public User() {};
	
	public User(String username, String password, String firstName, String lastName, boolean isPrivate, List<Room> recentRooms)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isPrivate = isPrivate;
		this.setRecentlyJoinedRooms(recentRooms);
	}
	
	@Id	
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="user_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="isPrivate")
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="recent_rooms", joinColumns = {	@JoinColumn(name="user_id")	}, 
		inverseJoinColumns = { @JoinColumn(name="room_id")})
	public List<Room> getRecentlyJoinedRooms() {
		return recentlyJoinedRooms;
	}

	public void setRecentlyJoinedRooms(List<Room> recentlyJoinedRooms) {
		this.recentlyJoinedRooms = recentlyJoinedRooms;
	}
	
	@ManyToOne
	@JoinColumn(name="current_room_id")
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
