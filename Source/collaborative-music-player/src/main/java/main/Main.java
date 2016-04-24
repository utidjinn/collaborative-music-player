package main;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import database.DatabaseManager;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import model.Room;
import model.RoomConfiguration;
import model.User;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main 
{	
	
	private static DatabaseManager databaseManager;
	
    public static void main(String[] args) 
    {    	    	
    	databaseManager = new DatabaseManager();
    	
    	FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/freemarker-templates/"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		staticFileLocation("/root");
		
    	get("/", (request, response) -> 
    	{    		
    		final User user = databaseManager.getUserById(0);
    		Map<String, Object> attributes = new HashMap<>();
            attributes.put("user", user);
    		return new ModelAndView(attributes, "home.ftl");
    	}, freeMarkerEngine);
    	
    	post("/createRoom", (request, response) -> 
    	{
    		final RoomConfiguration roomConfiguration = new RoomConfiguration
    		(
    			request.queryParams("txt_roomname"),
    			1
    		);
    		final Room room = databaseManager.createRoom(roomConfiguration);
    		response.redirect("/room/"+room.getId());
    		return "Successfully created room. Redirecting...";
    	});
    	
    	get("/room/:roomId", (request, response) ->
    	{
    		final Room room = databaseManager.getRoomById(Integer.parseInt(request.params(":roomId")));
    		Map<String, Object> attributes = new HashMap<>();
            attributes.put("room", room);
    		return new ModelAndView(attributes, "room.ftl");
    	}, freeMarkerEngine);
    	
    	get("/user/:userId", (request, response) ->
    	{   		
    		final User user = databaseManager.getUserById(Integer.parseInt(request.params(":userId")));
    		Map<String, Object> attributes = new HashMap<>();
            attributes.put("user", user);
    		return new ModelAndView(attributes, "user.ftl");
    	});
    }
}
