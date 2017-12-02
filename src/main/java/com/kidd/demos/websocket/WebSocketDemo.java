package com.kidd.demos.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Kidd
 *         CreateTime 2017/12/2.
 */

@ServerEndpoint(value = "/")
public class WebSocketDemo {

    private Session session;

    @OnOpen
    public void open(Session session) {
        this.session = session;
        System.out.println("*** WebSocket opened from sessionId " + session.getId());
    }

    @OnMessage
    public void inMessage(String message) {
        System.out.println("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
    }

    @OnClose
    public void end() {
        System.out.println("*** WebSocket closed from sessionId " + this.session.getId());
    }

}
