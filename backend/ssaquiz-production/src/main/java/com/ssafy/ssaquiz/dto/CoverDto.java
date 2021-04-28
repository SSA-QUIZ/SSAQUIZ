package com.ssafy.ssaquiz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;

@Builder
@ToString
@Getter
public class CoverDto {
    private String id;

    private String title;
}
