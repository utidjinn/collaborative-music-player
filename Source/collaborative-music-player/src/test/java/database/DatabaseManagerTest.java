package database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Room;
import model.RoomConfiguration;
import model.Song;
import model.User;

public class DatabaseManagerTest {
	private static final String HIBERNATE_TEST_CFG = "hibernate.cfg.xml";
	private DatabaseManager databaseManager;
	
	@Before
	public void setUp() throws SQLException
	{
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem//localhost","sa","1");
		Statement statement = connection.createStatement();
		String drop = "TRUNCATE SCHEMA public AND COMMIT";
		statement.executeUpdate(drop);
		connection.close();	
		databaseManager = new DatabaseManager(HIBERNATE_TEST_CFG);
	}
	
	@Test
	public void testCreateUser()
	{
		final User user = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		
		assertTrue(user.getUsername().equals("username"));
		assertTrue(user.getPassword().equals("password"));
		assertTrue(user.getFirstName().equals("firstName"));
		assertTrue(user.getLastName().equals("lastName"));
		assertFalse(user.isPrivate());
		assertNotNull(user.getRecentlyJoinedRooms());
		assertNull(user.getCurrentRoom());
	}
	
	@Test
	public void testCreateUserWithEmptyParamsNull()
	{
		final User user = databaseManager.createUser(null,"password", "firstName", "lastName", false);
		assertTrue(user == null);
	}
	
	@Test
	public void testCreateUserWithEmptyParamsEmptyString()
	{
		final User user = databaseManager.createUser("","password", "firstName", "lastName", false);
		assertTrue(user == null);
	}
	
	@Test
	public void testCreateTwoUsersWithSameName()
	{
		final User user1 = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		final User user2 = databaseManager.createUser("diff", "diff", "firstName", "lastName", false);
		assertNotNull(user1);
		assertNotNull(user2);
		assertTrue(user1.getId() != user2.getId());
	}
	
	@Test
	public void testGetUserById()
	{
		final User user = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		
		final User searchUser = databaseManager.getUserById(user.getId());
		
		assertTrue(searchUser.getId() == user.getId());
	}
	
	@Test
	public void testGetUserByIdWithNoUsersInDatabase()
	{
		final User user = databaseManager.getUserById(1);		
		assertNull(user);
	}
	
	@Test
	public void testGetUserByIdWithTwoUsers()
	{
		final User user1 = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		final User user2 = databaseManager.createUser("diff", "diff", "firstName", "lastName", false);
		
		final User searchForUser1 = databaseManager.getUserById(user1.getId());
		assertTrue(searchForUser1.getId() == user1.getId());
		
		final User searchForUser2 = databaseManager.getUserById(user2.getId());
		assertTrue(searchForUser2.getId() == user2.getId());
	}
	
	@Test
	public void testCreateRoom()
	{
		RoomConfiguration roomConfiguration = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room = databaseManager.createRoom(roomConfiguration);
		assertNotNull(room);
		assertTrue(room.getHostId() == 1);
		assertTrue(room.getName().equals("New Room"));
	}
	
	@Test
	public void testCreateRoomWithSameNames()
	{
		RoomConfiguration roomConfiguration1 = new RoomConfiguration
		(
			"New Room",
			1
		);
		RoomConfiguration roomConfiguration2 = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room1 = databaseManager.createRoom(roomConfiguration1);
		final Room room2 = databaseManager.createRoom(roomConfiguration2);
		
		assertNotNull(room1);
		assertNotNull(room2);
		
		assertTrue(room1.getId() != room2.getId());
	}
	
	@Test
	public void testGetRoomById()
	{
		RoomConfiguration roomConfiguration = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room = databaseManager.createRoom(roomConfiguration);
		
		final Room searchForRoom = databaseManager.getRoomById(room.getId());
		assertNotNull(searchForRoom);
		assertTrue(searchForRoom.getId() == room.getId());
	}
	
	@Test
	public void testGetRoomByIdWithNoCreatedRooms()
	{
		final Room room = databaseManager.getRoomById(1);
		assertNull(room);
	}
	
	@Test
	public void testSubmitSongWithoutExistingRoom()
	{
		final Song song = databaseManager.submitSong("link", 1, 2);
		assertNull(song);
	}
	
	@Test
	public void testSubmitSongToCreatedRoom()
	{
		RoomConfiguration roomConfiguration = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room = databaseManager.createRoom(roomConfiguration);
		
		final Song song = databaseManager.submitSong("link", room.getId(), 2);
		
		final Room searchForRoom = databaseManager.getRoomById(room.getId());
		assertTrue(searchForRoom.getPlaylist().get(0).getId() == song.getId());
	}
	
	@Test
	public void testSubmitSongWithBadParam()
	{
		final Song song = databaseManager.submitSong("", 1, 2);
		assertNull(song);
		final Song song2 = databaseManager.submitSong(null, 1, 2);
		assertNull(song2);
	}
	
	@Test
	public void testAddUserToRoom()
	{
		RoomConfiguration roomConfiguration = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room = databaseManager.createRoom(roomConfiguration);
		final User user = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		
		final Room returnedRoom = databaseManager.addUserToRoom(room.getId(), user.getId());
		
		assertNotNull(returnedRoom);
		assertNotNull(returnedRoom.getCurrentRoomUsers());
		assertTrue(returnedRoom.getCurrentRoomUsers().size() == 1);
		assertTrue(returnedRoom.getCurrentRoomUsers().get(0).getId() == user.getId());
		
		
		final User updatedUser = databaseManager.getUserById(user.getId());
		
		assertNotNull(updatedUser);
		assertTrue(updatedUser.getCurrentRoom().getId() == room.getId());
		assertTrue(updatedUser.getRecentlyJoinedRooms().size() == 1);
		assertTrue(updatedUser.getRecentlyJoinedRooms().get(0).getId() == returnedRoom.getId());
	}
	
	@Test
	public void testAddUserToRoomWhereRoomDoesNotExist()
	{
		final User user = databaseManager.createUser("username", "password", "firstName", "lastName", false);
		
		final Room returnedRoom = databaseManager.addUserToRoom(1, user.getId());
		
		assertNull(returnedRoom);
		
		final User updatedUser = databaseManager.getUserById(user.getId());
		assertTrue(updatedUser.getRecentlyJoinedRooms().size() == 0);
	}
	
	@Test
	public void testAddUserToRoomWhereUserDoesNotExist()
	{
		RoomConfiguration roomConfiguration = new RoomConfiguration
		(
			"New Room",
			1
		);
		final Room room = databaseManager.createRoom(roomConfiguration);
		final Room returnedRoom = databaseManager.addUserToRoom(room.getId(), 1);
		
		assertNull(returnedRoom);
		
		final Room updatedRoom = databaseManager.getRoomById(room.getId());
		assertTrue(updatedRoom.getCurrentRoomUsers().size() == 0);
	}
}
