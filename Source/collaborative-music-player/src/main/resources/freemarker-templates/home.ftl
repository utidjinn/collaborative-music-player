<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="/javascript/jquery.min.js"></script>
    <script src="/javascript/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/global-stylesheet.css">
    <title>Collaborative Music Player</title>
  </head>
  <body>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Collaborative-Music-Player</a>
          <p class="navbar-text text-center"><span class="glyphicon glyphicon-user" aria-hidden="true" />
          Signed in as ${user.username} : PW(${user.password}) </p>
        </div>
        <form class="navbar-form navbar-right" role="search">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search...">
          </div>
          <span class="input-group-btn">
            <button class="btn btn-default btn-submit" type="button">
              <i class="fa fa-search"></i>
            </button>
          </span>
        </form>
        <button type="button" class="btn btn-default navbar-btn btn-createRoom">Create Room</button>
      </nav>
    </body>
  </html>