var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#coordinates").html("");
}


function connect() {
    var socket = new SockJS('/hockey');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/player/move', function (coordinates) {
            const parse = JSON.parse(coordinates.body);
            showCoordinate(parse.xposition, parse.yposition);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/server/hockey", {}, JSON.stringify({'id': $("#name").val()}));
}

function showCoordinate(x, y) {
    $("#coordinates").append(`<tr><td>${x} ${y}</td></tr>`);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

