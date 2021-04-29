package com.ssafy.ssaquiz.dto;

import com.ssafy.ssaquiz.model.Slide;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Setter
@Getter
public class WorkbookDto {
    private String id;

    private long userId;

    private String workbookTitle;

    private List<Slide> slideList = new ArrayList<>();
}
