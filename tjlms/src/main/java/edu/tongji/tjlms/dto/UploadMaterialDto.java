package edu.tongji.tjlms.dto;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMaterialDto {
    private Integer labId;
    private String uploader;
    private String location;
    private String name;
}
