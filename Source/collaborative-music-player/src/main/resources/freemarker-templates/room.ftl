<!DOCTYPE html>
<html>
<head>
<title>Collaborative Music Player</title>
</head>
<body>

<form action="/room/${room.id}/submitSong" method="POST">
<input class="form-control" id="songLink" name="songLink" placeholder="Song Link" type="text" value="" />
<input id="btn_submitSong" name="btn_submitSong" type="submit" class="btn btn-default" value="Submit Song" />
</form>

</body>
</html>