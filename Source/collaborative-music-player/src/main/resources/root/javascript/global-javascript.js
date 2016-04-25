$(function() {
    $('#btn-create-room').click(function() {
        console.log('clicked');
        $('#createRoom-modal').modal(options);
        var options = {
          "backdrop": "true",
          "keyboard": "true",
          "show": "true"
        };
    });


});
