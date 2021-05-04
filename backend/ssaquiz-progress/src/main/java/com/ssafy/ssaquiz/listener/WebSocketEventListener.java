package com.ssafy.ssaquiz.listener;

import com.ssafy.ssaquiz.model.Message;
import com.ssafy.ssaquiz.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

// 소켓 연결(socket connect) 그리고 소켓 연결 끊기(disconnect) 이벤트를 수신하여
// 사용자가 채팅방을 참여(JOIN)하거나 떠날때(LEAVE)의 이벤트를 logging 하거나 broadcast 할 수 있음
@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // 이미 ChatController의 addUser()메소드에서 사용자 참여 이벤트를 broadcast하였기 때문에
    // handleWebSocketConnectListener()에서 사용하는 SessionConnected 이벤트 에서는 별다른 동작 없이 logging 처리
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("사용자 입장");
    }

    // SessionDisconnect 이벤트를 감지하여 웹 소켓 세션에서 사용자 이름을 추출하고
    // 연결된 모든 클라이언트에게 사용자 퇴장 이벤트를 broadcast
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            logger.info("사용자 퇴장 (" + username + ")");

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setSender(username);

            messagingTemplate.convertAndSend("/pin", message);
        }
    }
}