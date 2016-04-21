package main;
import static spark.Spark.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import models.User;

public class Main {	
	private static SessionFactory sessionFactory;
	
    public static void main(String[] args) {
    	System.out.println(System.getProperty("user.dir"));
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
		
    	staticFileLocation("/root");
    	get("/", (request, response) -> 
    	{
    		Session session = sessionFactory.openSession();
    		session.beginTransaction();
    		session.save( new User("utidjinn", "password", "Ian", "Crutcher", false));
    		session.getTransaction().commit();
    		session.close();
    		response.redirect("/home.html");
    		return null;
    	});
    }
}
