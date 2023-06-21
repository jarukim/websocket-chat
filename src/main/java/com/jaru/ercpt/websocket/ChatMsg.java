package com.jaru.ercpt.websocket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMsg {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType msgType;
    private String roomId;
    private String sender;
    private String msg;
}
