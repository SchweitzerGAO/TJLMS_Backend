package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNoticeDto {
    private Integer id;
    private String title;

    public GetNoticeDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
