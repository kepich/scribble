const url = 'http://localhost:8080';
let stompClient;
let newMessages = new Array();

let owner;

function redrawChat() {
    const elements = document.getElementsByClassName("messagesConsole");
    while (elements.length > 0) elements[0].remove();

    const messagesList = document.getElementById("messagesList");
    newMessages.forEach(element => {
        var tag = document.createElement("div");
        tag.classList.add("messagesConsole");
        tag.innerText = JSON.stringify(element);
        messagesList.appendChild(tag);
    });
}

function connectToChat() {
    console.log("connecting to chat...")
    let selectedRoom = document.getElementById("roomId").value;
    let socket = new SockJS(url + '/game');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + selectedRoom, function (response) {
            newMessages.push(JSON.parse(response.body));
            redrawChat();
            console.log(JSON.parse(response.body));
        });
    });
}

function sendMsg() {
    let selectedRoom = document.getElementById("roomId").value;
    let message = document.getElementById("message").value;

    console.log("Send message: ", message, ", ", owner);

    stompClient.send("/app/game/" + selectedRoom, {}, JSON.stringify({
        eventType: message,
        player: owner
    }));
}

function connectToTheRoom() {
    let selectedRoom = document.getElementById("roomId").value;
    let userName = document.getElementById("name").value;
    $.ajax({
      url: url + "/room/connect/",
      type: "POST",
      data: JSON.stringify({ roomId: selectedRoom, player: { name: userName } }),
      contentType:"application/json; charset=utf-8",
      dataType:"json",
      success: function(response){
        console.log("Response: ", response);
        owner = response.player;
        connectToChat();
      }
    })
}

function createRoom() {
    let userName = document.getElementById("name").value;
    $.ajax({
      url: url + "/room/create/",
      type: "POST",
      data: JSON.stringify({ name: userName }),
      contentType:"application/json; charset=utf-8",
      dataType:"json",
      success: function(response){
        document.getElementById("roomId").value = response.room.roomId;
        console.log("Response: ", response.player);

        owner = response.player;

        connectToTheRoom();
      }
    })
}
