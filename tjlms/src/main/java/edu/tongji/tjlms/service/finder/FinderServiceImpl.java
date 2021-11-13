package edu.tongji.tjlms.service.finder;

import edu.tongji.tjlms.model.StudentWithoutGradeEntity;
import edu.tongji.tjlms.model.TeachingStudentEntity;
import edu.tongji.tjlms.repository.StudentFinderRepository;
import edu.tongji.tjlms.repository.StudentWithoutGradeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinderServiceImpl implements FinderService{
    @Resource
    StudentFinderRepository studentFinderRepository;
    @Resource
    StudentWithoutGradeRepository studentWithoutGradeRepository;
    @Override
    public List<TeachingStudentEntity> findAllStudent(String teacherId,Integer labId)
    {
        Specification<TeachingStudentEntity> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            Predicate teacher_id = criteriaBuilder.equal(root.get("teacherId").as(String.class),teacherId);
            Predicate resp_id = criteriaBuilder.equal(root.get("respId").as(String.class),teacherId);
            Predicate assist_id = criteriaBuilder.equal(root.get("assistId").as(String.class),teacherId);
            Predicate lab_id = criteriaBuilder.equal(root.get("labId").as(Integer.class),labId);
            predicates.add(teacher_id);
            predicates.add(resp_id);
            predicates.add(assist_id);

            Predicate[] pArray = new Predicate[predicates.size()];
            Predicate pAnd = criteriaBuilder.and(lab_id);
            Predicate pOr = criteriaBuilder.or(predicates.toArray(pArray));
            return criteriaQuery.where(pAnd,pOr).getRestriction();
        };
        List<TeachingStudentEntity> ret = studentFinderRepository.findAll(specification);
        if(ret.isEmpty())
        {
            List<StudentWithoutGradeEntity> list = studentWithoutGradeRepository.findAll();

            for(StudentWithoutGradeEntity student:list)
            {
                TeachingStudentEntity temp = new TeachingStudentEntity();
                temp.setVisible(null);
                temp.setScore(null);
                temp.setNote(null);
                temp.setId(student.getId());
                temp.setTeacherId(student.getTeacherId());
                temp.setName(student.getName());
                temp.setAssistId(student.getAssistId());
                temp.setClassId(student.getClassId());
                temp.setRespId(student.getRespId());
                temp.setLabId(labId);
                ret.add(temp);
            }
        }
        return ret;
    }
}