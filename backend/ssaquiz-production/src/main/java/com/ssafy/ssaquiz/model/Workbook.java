package com.ssafy.ssaquiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="workbook")
@ToString
@Builder
@Setter
@Getter
public class Workbook {
    @Id
    private ObjectId id;

    private long userId;

    private String workbookTitle;

    private List<Slide> slideList = new ArrayList<>();
}
