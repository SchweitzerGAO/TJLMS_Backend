package edu.tongji.tjlms.service.material;

import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaterialService {
    String uploadMaterial(UploadMaterialDto umd);
    String deleteMaterial(String location);

    Page<MaterialEntity> getAllMaterials(Integer pageNum,Integer pageSize);

    List<MaterialEntity> getAllByLabId(Integer labId);
    Page<MaterialEntity> getAllByLabId(Integer labId, Integer pageNum,Integer pageSize);

    Page<MaterialEntity> getAllByUploader(String uploader,Integer pageNum,Integer pageSize);

    List<MaterialEntity> getLatestFive();
}
