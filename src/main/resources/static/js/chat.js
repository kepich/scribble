const url = 'http://localhost:8080';
let stompClient;
let newMessages = new Map();

function connectToChat() {
    console.log("connecting to chat...")
    let selectedRoom = document.getElementById("roomId").value;
    let socket = new SockJS(url + '/game');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + selectedRoom, function (response) {
            console.log(JSON.parse(response.body));
        });
    });
}

function sendMsg() {
    let selectedRoom = document.getElementById("roomId").value;
    let message = document.getElementById("message").value;
    stompClient.send("/app/game/" + selectedRoom, {}, JSON.stringify({
        eventType: message
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
        document.getElementById("roomId").value = response.roomId;
        console.log("Response: ", response);

        connectToTheRoom();
      }
    })
}



function connectToSocket() {
    connectToChat();
}

function selectUser(userName) {
    console.log("selecting users: " + userName);
    selectedRoom = userName;
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedRoomId').html('');
    $('#selectedRoomId').append(userName);
}

function selectUserManual() {
    selectUser(document.getElementById("roomId").value);
}

function registration() {
    let userName = document.getElementById("userName").value;
    $.get(url + "/room/create/" + userName, function (response) {
        console.log("Response: ", response);
        connectToChat(response.roomId);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function fetchAll() {
    $.get(url + "/player/fetch-all", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')"><li class="clearfix">\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}
