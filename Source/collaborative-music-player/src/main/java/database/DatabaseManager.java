package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import model.RoomConfiguration;
import model.Song;
import model.RecentRoomEntry;
import model.Room;
import model.User;

public class DatabaseManager {
	private SessionFactory sessionFactory;

	public DatabaseManager(String configFile)
	{
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure(configFile) // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	public User createUser(String username, String password, String firstName, String lastName, boolean isPrivate)
	{
		if ((username == null || username.isEmpty())
			|| (password == null || password.isEmpty())
			|| (firstName == null || firstName.isEmpty())
			|| (lastName == null || lastName.isEmpty()))
		{
			return null;
		}
		final User newUser = new User(username, password, firstName, lastName, isPrivate, new ArrayList<Room>());		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newUser);
		session.getTransaction().commit();
		session.close();
		return newUser;
	}
	
	public User getUserById(int userId)
	{
		final Session session = sessionFactory.openSession();
		final List list =  session.createCriteria(User.class)
				.add(Restrictions.eq("id", userId))
				.list();		
		session.close();
		if (list.size() != 1)
		{
			return null;
		}
		final User user = (User) list.get(0);
		return user;
	}

	public Room getRoomById(int roomId)
	{
		final Session session = sessionFactory.openSession();
		final List list = session.createCriteria(Room.class)
				.add(Restrictions.eq("id", roomId))
				.list();
		if (list.size() != 1)
		{
			return null;
		}
		final Room returnedRoom = (Room) list.get(0);
		Hibernate.initialize(returnedRoom.getPlaylist());
		Hibernate.initialize(returnedRoom.getHistory());
		session.close();
		return returnedRoom;
	}

	public Room createRoom(RoomConfiguration roomConfiguration)
	{
		if (roomConfiguration.getRoomName() == null || roomConfiguration.getRoomName().isEmpty())
		{
			return null;
		}
		Room newRoom = new Room
		(
			roomConfiguration.getRoomName(),
			System.currentTimeMillis(),
			roomConfiguration.getCreatorId()
		);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newRoom);
		session.getTransaction().commit();
		session.close();

		return newRoom;
	}

	public Room addUserToRoom(int roomId, int userId) 
	{
		// Pull user and room from the database
		final User user = getUserById(userId);
		final Room room = getRoomById(roomId);
		
		if (user == null || room == null)
		{
			return null;
		}
		
		room.getCurrentRoomUsers().add(user);
		user.setCurrentRoom(room);
		
		final RecentRoomEntry newRecentRoomEntry = new RecentRoomEntry(userId, roomId);		
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newRecentRoomEntry);
		session.update(user);
		// we don't have to update the room, as it will be persisted via the user update
		session.getTransaction().commit();
		session.close();		
		return room;
	}

	public Song submitSong(String songLink, int roomId, int userId)
	{
		if (songLink == null || songLink.isEmpty())
		{
			return null;
		}
		
		final Song newSong = new Song(userId, roomId, "Never Gonna Give You Up", songLink, 10);
		final Room room = getRoomById(roomId);
		if (room == null)
		{
			return null;
		}
		room.getPlaylist().add(newSong);
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(room); // room will persist song to relevant tables?
		session.getTransaction().commit();
		session.close();
		return newSong;
	}
	
	public List<Room> getListofRooms(int max)
	{
		final Session session = sessionFactory.openSession();
		final List<Room> returnedRooms = session.createCriteria(Room.class).setMaxResults(10)
				.list();
		session.close();
		return returnedRooms;
	}
}
