<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="/javascript/jquery.min.js"></script>
    <script src="/javascript/bootstrap.min.js"></script>
    <script src="/javascript/global-javascript.js"></script>
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/global-stylesheet.css">
    <title>Collaborative Music Player</title>
  </head>
  <body>
  <!-- header -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Collaborative-Music-Player</a>
          <p class="navbar-text text-center"><span class="glyphicon glyphicon-user" aria-hidden="true" />
          Signed in as ${user.username}</p>
          <form class="navbar-form navbar-right form-search" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button class="btn btn-default btn-submit" type="button">
                <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
          <button id="btn-create-room" type="button" class="btn btn-default navbar-btn btn-createRoom">Create Room</button>

        </div>
      </div>
    </nav>

    <!-- sidebar activity -->
<!--     <div class="col-xs-7 col-sm-3 col-md-3 sidebar sidebar-right sidebar-animate" style="float: right;min-height:100%;" >
      <h2>Activity Feed</h2>
      <div class="activity-feed">
        <div class="feed-item">
          <div class="date">April 20</div>
          <div class="text">${user.firstName} listened to <a href="https://www.youtube.com/watch?v=L2sH_0LZOUM">"Ice Cube &amp Dr Dre "California Love" Coachella 2016"</a></div>
        </div>
        <div class="feed-item">
          <div class="date">April 10</div>
          <div class="text">${user.firstName} listened to <a href="https://www.youtube.com/watch?v=cMTAUr3Nm6I">"Meghan Trainor - NO"</a></div>
        </div>
      </div>
    </div>
 -->

    <!-- profile card -->
    <div class="container profile-card">
      <div class="row">
        <div class="col-sm-4 col-md-6">
          <div class="card">
            <canvas class="header-bg" width="250" height="70" id="header-blur"></canvas>
            <div class="avatar">
              <img src="/images/user-128.png" alt="profile-img" />
            </div>
            <div class="content">
              <p>${user.firstName} ${user.lastName}<br>
              ${user.username}</p>
              <p><button type="button" class="btn btn-default">Profile</button></p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- navigation tabs -->
    <!-- Added Songs
    Recent Rooms
    Liked Songs -->
    <ul class="nav nav-tabs">
      <li class="active"><a data-toggle="tab" href="#addedSongs-tab">Added Songs</a></li>
      <li><a data-toggle="tab" href="#recentRooms-tab">Recent Rooms</a></li>
      <li><a data-toggle="tab" href="#likedSongs-tab">Liked Songs</a></li>
    </ul>
    <div class="tab-content">
      <div id="addedSongs-tab" class="tab-pane fade in active">
        <h3>Added Songs</h3>
        <p>Some content in playlist.</p>
      </div>
      <div id="recentRooms-tab" class="tab-pane fade">
        <h3>Recent Rooms</h3>
        <p>Some content in history.</p>
      </div>
      <div id="likedSongs-tab" class="tab-pane fade">
        <h3>Liked Songs</h3>
        <p>Some content in users.</p>
      </div>
    </div>


  </body>
</html>