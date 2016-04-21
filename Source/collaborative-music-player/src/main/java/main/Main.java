package main;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import models.User;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

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
		
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/root/"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
    	get("/", (request, response) -> 
    	{
    		// Example save of object to db
    		Session session = sessionFactory.openSession();
    		session.beginTransaction();
    		session.save( new User("utidjinn", "password", "Ian", "Crutcher", false));
    		session.getTransaction().commit();
    		session.close();
    		
    		// Example query on db
    		session = sessionFactory.openSession();
    		User user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", "utidjinn")).list().get(0);
    		
    		Map<String, Object> attributes = new HashMap<>();
            attributes.put("user", user);
    		return new ModelAndView(attributes, "home.ftl");
    	}, freeMarkerEngine);
    }
}
