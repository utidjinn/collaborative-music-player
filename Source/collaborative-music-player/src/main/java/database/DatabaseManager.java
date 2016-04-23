package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import models.Room;
import models.User;

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
		/*Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new User("utidjinn", "password", "Ian", "Crutcher", false));
		session.getTransaction().commit();
		session.close();*/
	}
	
	public User getUserById(int userId)
	{
		final Session session = sessionFactory.openSession();
		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("user_id", userId))
				.list()
				.get(0);	
	}
	
	public Room getRoomById(int roomId)
	{
		final Session session = sessionFactory.openSession();
		final Room returnedRoom = (Room) session.createCriteria(Room.class)
				.add(Restrictions.eq("room_id", roomId))
				.list()
				.get(0);
		return returnedRoom;
	}
}
