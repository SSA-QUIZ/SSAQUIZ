package com.ssafy.ssaquiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트 등록
    // 퀴즈 입장하기 버튼 누르면 /connect 엔드 포인트에 연결
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는 데 사용될 메시지 브로커 구성
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // "/quiz" 시작되는 메시지가 message-handling methods으로 라우팅 되어야 한다는 것을 명시
        // "/quiz" 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는 Controller의 @MessageMapping 어노테이션이 달린 메소드로 라우팅
        // "/quiz/room.sendMessage" 인 메세지는 Controller의 sendMessage()로 라우팅
        // "/quiz/room.addUser" 인 메시지는 Controller의 addUser()로 라우팅
        // 클라이언트 입장에서는 보낼때, 서버입장에서는 받을때
        registry.setApplicationDestinationPrefixes("/quiz");

        // "/pin" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
        // 클라이언트 입장에서는 받을때, 서버입장에서는 보낼때
        registry.enableSimpleBroker("/pin");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(160 * 64 * 1024); // default : 64 * 1024
        registration.setSendTimeLimit(100 * 10000); // default : 10 * 10000
        registration.setSendBufferSizeLimit(3* 512 * 1024); // default : 512 * 1024
    }
}