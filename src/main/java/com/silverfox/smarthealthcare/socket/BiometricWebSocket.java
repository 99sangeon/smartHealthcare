package com.silverfox.smarthealthcare.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silverfox.smarthealthcare.dto.BiometricRequest;
import com.silverfox.smarthealthcare.entity.Biometric;
import com.silverfox.smarthealthcare.service.BiometricService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BiometricWebSocket extends TextWebSocketHandler {

    private final BiometricService biometricService;
    private final ObjectMapper objectMapper;

    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[{}] 보행보조차 재활 시작", session);
        list.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        BiometricRequest biometricRequest = objectMapper.readValue(message.asBytes(), BiometricRequest.class);
        biometricService.saveBiometric(biometricRequest);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("[{}] 보행보조차 재활 종료", session);
        list.remove(session);
    }
}
