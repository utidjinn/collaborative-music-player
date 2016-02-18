Definitions
        * Room
        * Session that users can enter. Rooms contain users and a host, which can affect the playlist
    * User
        * Username is public, however music preferences (user data) can be set to private
    * Token
    * (room-creator)
    * Song
    * platform undefined.

Functional Requirements
    1. Music playback control
        a. Add
            i. User in a room can add to the end of the playlist
            ii. Configurable in Room Settings
                1. User get X tokens, when song is added token is deducted, when song finishes playing token is re-added
                2. Other users can move up other user’s songs by using a token, once song is played all users get tokens back
    b. Remove
        i. Users cannot remove any songs
        i. Host can remove a song
    c. Play
        i. If playlist finishes and no songs have been added, playlist will be replayed
            1. However, if playlist finishes and users want to add songs it will be added to the top of the playlist
    d. Next
        i. Host has ability to move to the next song (skip song)

Web App

History of music just played
    - Application should be able to hold a history of the music played

Room creation
    1. Any user will be to create room, person will be known as host
    2. Host can share room by (Room can or cannot have password)
        a. url
        b. direct sharing via existing username
    3. Room can have unlimited of users and songs

Room settings can be configured (some only on creation, some during session)


Room access control
    1. Passwords
        a Room may have password
    2. Kicking ability
        a. Host will be able to remove/ban any user from room
    3. User can only be in one room at a time

Preview of songs
    1. Playlist should be available for all users to view

Room Privileges (Host/Attendee)
	1.  Controllable via Host
	2.  Ability to migrate host - Host should be able to choose a new host

Music can still play even if Host leaves room

User Account
	1. previous liked songs - account specific
	2. recently listened to songs
	3. added songs

recently attended rooms

Ability to view other accounts - User can see other people’s accounts and music preferences unless account is private

Room Sharing to other users - users can share room to other users

Ability to group users into groups - users can group certain users into a group by searching for username

**Non-Functional Reqs**

        -Core api that can be consumed on different platforms
    -Java
    -Response time on actions in the room should be within a second

    -Music from youtube
