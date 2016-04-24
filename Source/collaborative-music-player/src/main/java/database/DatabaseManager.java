package database;

import model.Room;
import model.RoomConfiguration;
import model.User;

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

	public DatabaseManager()
	{
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
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

	public User getUserById(int userId)
	{
		final Session session = sessionFactory.openSession();
		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("id", userId))
				.list()
				.get(0);
	}

	public Room getRoomById(int roomId)
	{
		final Session session = sessionFactory.openSession();
		final Room returnedRoom = (Room) session.createCriteria(Room.class)
				.add(Restrictions.eq("id", roomId))
				.list()
				.get(0);
		return returnedRoom;
	}

	public Room createRoom(RoomConfiguration roomConfiguration)
	{
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

	public void addRoomToUsersRecentRooms(int roomId, int userId)
	{
		final RecentRoomEntry newRecentRoomEntry = new RecentRoomEntry(userId, roomId);

		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newRecentRoomEntry);
		session.getTransaction().commit();
		session.close();
	}

	public void submitSong(String songLink, int roomId, int userId)
	{
		final Song newSong = new Song(userId, roomId, "Never Gonna Give You Up", songLink, 10);
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newSong);
		session.getTransaction().commit();
		session.close();
	}
}
