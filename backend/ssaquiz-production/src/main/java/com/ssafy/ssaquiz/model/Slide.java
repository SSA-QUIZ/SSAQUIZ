package com.ssafy.ssaquiz.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Setter
@Getter
public class Slide {
    private String category;
    private String question;
    private String imagePath;
    private String answer;
    private List<String> orderedAnswer = new ArrayList<>();
    private List<String> answerList = new ArrayList<>();
    private int time;
    private float scoreFactor;
    private String type;
}