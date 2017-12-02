package com.kidd.demos.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Kidd
 *         CreateTime 2017/12/2.
 */
@Log4j2
public class MyWebSocketHandler extends TextWebSocketHandler{

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String text = message.getPayload();
        log.info(text);
        session.sendMessage(message);
    }
}
