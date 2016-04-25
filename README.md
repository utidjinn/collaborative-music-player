# collaborative-music-player
##CS3704 Group Project

**Members**
* Alisher Pzylbekov (alisherp@vt.edu)
* Ian Crutcher (crutcher@vt.edu)
* Jaemin Seo (jm199seo@vt.edu)
* Omid Anvar (omid8@vt.edu)
* Brian Clarke (bclarke1@vt.edu)


Directions to run project
You must have mysql installed on your machine. After setting up the mysql server, create a user called cmp with password SPRING2016. Give it all permissions. Then, run the musicplayer.sql and initialize.sql scripts. This will create a musicplayer database on the sql server.

There are two projects in the submission. collaborative-music-player contains the website code and unit tests, while seleniumtests contains the use case tests. 

Import these projects into eclipse. Run the website as a java application. It uses port 4567, so you can't have something using that port. Run as a junit suite to run the unit tests, which are in src/test/java.

To run the selenium tests, first run the application. Then run the selenium test project. 

Dependencies are controlled via maven, so you must have the maven plugin installed on eclipse to pull in these dependencies. 
