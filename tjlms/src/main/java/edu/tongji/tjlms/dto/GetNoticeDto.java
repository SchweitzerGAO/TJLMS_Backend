package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNoticeDto {
    private Integer id;
    private String title;
    private String time;

    public GetNoticeDto(Integer id, String title,String time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }
}
