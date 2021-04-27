package com.ssafy.ssaquiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection="workbook")
@ToString
@Builder
@Setter
@Getter
public class Workbook {
    long id;
    String workbookTitle;
    long userId;
    Map<Integer, Slide> slides = new HashMap<>();
}
