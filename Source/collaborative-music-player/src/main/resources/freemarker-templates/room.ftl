<!DOCTYPE html>
<html>
<head>
    <script src="/javascript/jquery.min.js"></script>
    <script src="/javascript/global-javascript.js"></script>
<title>Collaborative Music Player</title>
</head>
<body>

<form action="/room/${room.id}/playlist/submitSong" method="POST">
<input class="form-control" id="songLink" name="songLink" placeholder="Song Link" type="text" value="" />
<input id="btn_submitSong" name="btn_submitSong" type="submit" class="btn btn-default" value="Submit Song" />
</form>

</body>
</html>