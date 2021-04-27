package com.ssafy.ssaquiz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Setter
@Getter
public class Slide {
	String answer;
	List<String> orderedAnswer = new ArrayList<>();
	List<String> answerList = new ArrayList<>();
	String category;
	String question;
	String resourceUrl;
	Map<String, String> settings = new HashMap<>();
}