const url = 'http://localhost:8080';
let stompClient;
let newMessages = new Array();
let rooms = new Array();
selectedRoom = "";

let owner;
let room;
let api;

function createChatTdWithText(text) {
    var tag = document.createElement("td");
    tag.classList.add("chatTd");
    tag.innerText = text;

    return tag;
}

function createRoomTdWithText(text) {
    var tag = document.createElement("td");
    tag.classList.add("roomTd");

    if (selectedRoom === text) {
        tag.classList.add("selectedRoom");
    }

    tag.innerText = text;

    return tag;
}

function getRoomList() {
    const elements = document.getElementsByClassName("roomRow");
    while (elements.length > 0) elements[0].remove();

    $.ajax({
      url: url + "/debug/all-rooms/",
      type: "GET",
      success: function(response){
        console.log("getRoomList");
        const messagesList = document.getElementById("roomListTable");
        rooms = response;
        rooms.forEach(element => {
            var tr = document.createElement("tr");
            tr.classList.add("roomRow");
            tr.appendChild(createRoomTdWithText(element.id));
            messagesList.appendChild(tr);
        });
      }
    })
}

function redrawChat() {
    const elements = document.getElementsByClassName("chatRow");
    while (elements.length > 0) elements[0].remove();

    const messagesList = document.getElementById("messagesList");
    var i = 1;
    newMessages.forEach(element => {
        var tr = document.createElement("tr");
        tr.classList.add("chatRow");
        tr.appendChild(createChatTdWithText(i + ")\t\t" + JSON.stringify(element)));
        i = i + 1;
        messagesList.appendChild(tr);
    });
    getRoomList();
}

function connectToChat() {
    let selectedRoom = document.getElementById("roomId").value;
    let socket = new SockJS(url + '/game');
    stompClient = Stomp.over(socket);

    // Хедеры для сессии, по которым я смогу отслеживать подключение человечка к комнате и его отключение
    stompClient.connect({roomId: room.id, playerId: owner.id}, function (frame) {
        console.log("connected to: " + frame);
        document.getElementById("roomId").style.border = "4px solid green";
        document.getElementById("isConnected").style.display = "inline-flex";
        newMessages = Array();
        redrawChat();

// Подписки на события
        stompClient.subscribe(api.subscribes.CONNECT + selectedRoom, function (response) {
            newMessages.push(JSON.parse(response.body));
            redrawChat();
        });

        stompClient.subscribe(api.subscribes.DISCONNECT + selectedRoom, function (response) {
            newMessages.push("DISCONNECT: " + response.body);
            redrawChat();
        });

        stompClient.subscribe(api.subscribes.SETTINGS + selectedRoom, function (response) {
            newMessages.push(JSON.parse(response.body));
            redrawChat();
        });
    });
}

// Отправка события изменения настроек комнаты
function sendMsgConfig() {
    selectedRoom = document.getElementById("roomId").value;
    let message = document.getElementById("messageConfig").value;

    stompClient.send(api.producers.SETTINGS + selectedRoom, {}, JSON.stringify({
        roundNumber: 1,
        drawTime: 30,
        words: [message],
        customWordsExcl: false
    }));
}

function connectToTheRoom() {
    selectedRoom = document.getElementById("roomId").value;
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
        room = response.room;
        api = response.api;
        document.getElementById("roomSettings").innerText = JSON.stringify(room.settings);
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
        document.getElementById("roomId").value = response.room.id;
        console.log("Response: ", response);

        owner = response.player;
        room = response.room;
        api = response.api;
        selectedRoom = response.room.id;

        document.getElementById("roomSettings").innerText = JSON.stringify(room.settings);
        connectToChat();
      }
    })
}
