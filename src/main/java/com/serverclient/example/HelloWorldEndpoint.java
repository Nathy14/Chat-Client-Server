package com.serverclient.example;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * This is the implementation of the WebSocket Server
 * @version 1.0
 * @author Natalia Mattos
 */

@ServerEndpoint("/hello")
//will be exposed by WebSocket
public class HelloWorldEndpoint {
    //to start mvn jetty:run
    
    //creating the constructor
    public HelloWorldEndpoint(){
        System.out.printf("class loaded" + this.getClass());
    }

    //all implementation of WebSockets should have four messages in it
    //onOpen is when the socket is open for the first time
    @OnOpen
    public void onOpen(Session session){
        System.out.printf("Session opened, id: %s%n",session.getId());
        try{
            /*getBasic Remote -> Return a reference a RemoteEndpoint object representing the peer
            of this conversation that is able to send messages synchronously to the peer.*/
            session.getBasicRemote().sendText("Hi there, we are successfully connected.");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    //onMessage is when a new message arrives to the WebSocket
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.printf("Message received. Session id: %s Message: %s%n", session.getId(), message);
        try{
            //sending the message back to the client
            session.getBasicRemote().sendText(String.format("We received your message: %s%n", message));
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    //onError is when something bad occurs in the code
    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

    //onClose is when the WebSocket is closed
    @OnClose
    public void onClose(Session session){
        System.out.printf("Session closed with id: %s%n", session.getId());
    }
}
