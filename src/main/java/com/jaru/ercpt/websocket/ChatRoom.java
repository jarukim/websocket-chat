package com.jaru.ercpt.websocket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMsg chatMsg, ChatService chatService) {
        if (chatMsg.getMsgType().equals(ChatMsg.MessageType.ENTER)) {
            sessions.add(session);
            chatMsg.setMsg(chatMsg.getSender() + "님이 입장했습니다.");
        }
        sendMsg(chatMsg, chatService);
    }

    public <T> void sendMsg(T msg, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMsg(session, msg));
    }
}
