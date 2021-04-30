package com.ssafy.ssaquiz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class InsertWorkbookDto {
    private long userId;

    private String workbookTitle;
}
