package edu.tongji.tjlms.service.material;

import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import edu.tongji.tjlms.repository.MaterialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService{
    @Resource
    MaterialRepository materialRepository;
    @Override
    public String uploadMaterial(UploadMaterialDto umd) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        materialRepository.save(new MaterialEntity(umd.getLocation(),umd.getLabId(),umd.getUploader(),umd.getName(),time));
        return "资料上传成功";
    }

    @Override
    public String deleteMaterial(Integer id) {
        materialRepository.deleteById(id);
        return "资料删除成功";
    }

    @Override
    public Page<MaterialEntity> getAllMaterials(Integer pageNum, Integer pageSize) {

        return materialRepository.findAll(PageRequest.of(pageNum-1,pageSize));
    }

    @Override
    public List<MaterialEntity> getAllByLabId(Integer labId) {
        return materialRepository.findAllByLabId(labId);
    }

    @Override
    public Page<MaterialEntity> getAllByLabId(Integer labId, Integer pageNum, Integer pageSize) {
        return materialRepository.findAllByLabId(labId,PageRequest.of(pageNum-1,pageSize));
    }

    @Override
    public Page<MaterialEntity> getAllByUploader(String uploader, Integer pageNum, Integer pageSize) {
        return materialRepository.findAllByUploader(uploader,PageRequest.of(pageNum-1,pageSize));
    }

    @Override
    public List<MaterialEntity> getLatestFive() {
        List<MaterialEntity> all = materialRepository.findAll(Sort.by(Sort.Direction.DESC,"uploadTime"));
        if(all.size() < 5)
        {
            return all;
        }
        return all.subList(0,5);
    }
}
