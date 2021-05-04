package com.ssafy.ssaquiz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Message {
    private MessageType type;
    private Object content;
    private String sender;
    private String quizNum;
}