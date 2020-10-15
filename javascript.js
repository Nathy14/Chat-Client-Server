
        var webSocket =  new WebSocket("ws://localhost:8080/example/hello");
        var msgField =  document.getElementById("messageField");
        var divMsg = document.getElementById("msg-box");

        function sendMsg(){
            var msgToSend = msgField.value;
            webSocket.send(msgToSend);
            divMsg.innerHTML += "<div style = 'color:red'>Client>" +msgToSend + "</div>"
            msgField.value = "";
        }

        webSocket.onmessage = function(message){
            divMsg.innerHTML += "Server> : " + message.data;
        }
        webSocket.onopen = function(){console.log("connection opened")};
        webSocket.onclose = function(){console.log("connection closed")};
        webSocket.onerror = function wserror(message){
            console.log("error: "+message);
        }