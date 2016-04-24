# Dump File
#
# Database is ported from MS Access
#--------------------------------------------------------
# Program Version 3.0.138

CREATE DATABASE IF NOT EXISTS `musicplayer`;
USE `musicplayer`;

#
# Table structure for table 'user'
#

DROP TABLE IF EXISTS `app_user`;

CREATE TABLE `app_user` (
  `user_id` INTEGER NOT NULL, 
  `username` VARCHAR(32) NOT NULL, 
  `password` VARCHAR(32) NOT NULL, 
  `first_name` VARCHAR(32) NOT NULL, 
  `last_name` VARCHAR(32) NOT NULL, 
  `isPrivate` TINYINT NOT NULL, 
  INDEX (`user_id`), 
  PRIMARY KEY (`user_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;


#
# Table structure for table 'added_songs'
#

DROP TABLE IF EXISTS `added_songs`;

CREATE TABLE `added_songs` (
  `user_id` INTEGER NOT NULL, 
  `song_id` INTEGER NOT NULL, 
  INDEX (`user_id`), 
  PRIMARY KEY (`user_id`, `song_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;


#
# Table structure for table 'song'
#

DROP TABLE IF EXISTS `song`;

CREATE TABLE `song` (
  `song_id` INTEGER NOT NULL, 
  `user_id` INTEGER NOT NULL, 
  `song_name` VARCHAR(32) NOT NULL, 
  `link` VARCHAR(512) NOT NULL, 
  `duration` INTEGER NOT NULL, 
  `room_history_id` INTEGER NOT NULL, 
  INDEX (`song_id`), 
  PRIMARY KEY (`song_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Table structure for table 'room'
#

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` INTEGER NOT NULL, 
  `room_name` VARCHAR(32) NOT NULL, 
  `date_created` BIGINT, 
  `host_user_id` INTEGER NOT NULL, 
  PRIMARY KEY (`room_id`), 
  INDEX (`room_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;


#
# Table structure for table 'room_to_users'
#

DROP TABLE IF EXISTS `room_to_users`;

CREATE TABLE `room_to_users` (
  `room_id` INTEGER NOT NULL, 
  `user_id` INTEGER NOT NULL, 
  PRIMARY KEY (`user_id`), 
  INDEX (`user_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Table structure for table 'recent_rooms'
#

DROP TABLE IF EXISTS `recent_rooms`;

CREATE TABLE `recent_rooms` (
  `id` INTEGER NOT NULL,
  `user_id` INTEGER NOT NULL,
  `room_id` INTEGER NOT NULL, 
  PRIMARY KEY (`id`), 
  INDEX (`user_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Table structure for table 'liked_songs'
#

DROP TABLE IF EXISTS `liked_songs`;

CREATE TABLE `liked_songs` (
  `user_id` INTEGER NOT NULL,
  `song_id` INTEGER NOT NULL, 
  PRIMARY KEY (`user_id`), 
  INDEX (`user_id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;