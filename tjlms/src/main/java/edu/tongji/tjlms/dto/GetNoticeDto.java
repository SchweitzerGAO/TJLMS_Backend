package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNoticeDto {
    private int id;
    private String title;

    public GetNoticeDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
