package main;
import static spark.Spark.*;

public class Main {	
    public static void main(String[] args) {
        staticFileLocation("/root");
    	get("/hello", (req, res) -> "Hello World");
    }
}
