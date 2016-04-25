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
    <div class="col-xs-7 col-sm-3 col-md-3 sidebar sidebar-right sidebar-animate" style="float: right;min-height:100%;" >
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

    <!-- youtube embed -->
    <!-- <div class="videoWrapper">
      <#list room.playlist as playlist>
      <div id="youtubePlayer">
        <p class="text-hide youtubeLink">${playlist.link}</p>
      	${playlist}
      </div>
      </#list>
    </div> -->
    <div class="youtubePlayer">
      <iframe width="600" height="400" src="https://www.youtube.com/embed/astISOttCQ0"></iframe>
    </div>


    <!-- navigation tabs -->
    <ul class="nav nav-tabs">
      <li class="active"><a data-toggle="tab" href="#playlist-tab">Playlist</a></li>
      <li><a data-toggle="tab" href="#history-tab">History</a></li>
      <li><a data-toggle="tab" href="#users-tab">Users</a></li>
    </ul>
    <div class="tab-content">
      <div id="playlist-tab" class="tab-pane fade in active">
        <h3>Playlist</h3>
        <#list room.playlist as playlist>
        <!-- <p>Some content in playlist.</p> -->
        Song : <a href="${playlist.link}">${playlist.name}</a>
        </#list>
      </div>
      <div id="history-tab" class="tab-pane fade">
        <h3>History</h3>
        <p>Some content in history.</p>
      </div>
      <div id="users-tab" class="tab-pane fade">
        <h3>Users</h3>
        <#list room.currentRoomUsers as roomUsers>
        <p>User : ${roomUsers.firstName} ${roomUsers.lastName} is online</p>
        </#list>
      </div>
    </div>

    <!-- Create room modal -->
    <div id="createRoom-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Create Room</h4>
          </div>
          <div class="modal-body">
            <p>One fine body&hellip;</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </body>
</html>