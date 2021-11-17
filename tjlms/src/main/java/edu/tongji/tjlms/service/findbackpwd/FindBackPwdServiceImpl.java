package edu.tongji.tjlms.service.findbackpwd;

import edu.tongji.tjlms.dto.FindBackPwdDto;
import edu.tongji.tjlms.model.AdminEntity;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.AdminRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.service.email.EmailSendServiceImpl;
import edu.tongji.tjlms.util.EncryptSha256Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class FindBackPwdServiceImpl implements FindBackPwdService {
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Resource
    AdminRepository adminRepository;

    @Override
    public String resetPwd(FindBackPwdDto fbpd) {
        String verificationCode = EmailSendServiceImpl.getVerificationCode();
        if(!fbpd.getVerificationCode().equals(verificationCode))
        {
            EmailSendServiceImpl.setVerificationCodeToEmpty();
            return "验证码错误";
        }
        EmailSendServiceImpl.setVerificationCodeToEmpty();
        int type = fbpd.getUserType();
        switch (type)
        {
            case 0:
            {
                Optional<AdminEntity> admin = adminRepository.findById(fbpd.getId());
                if(!admin.isPresent())
                {
                    return "未找到用户信息";
                }
                adminRepository.updatePwd(fbpd.getId(), EncryptSha256Util.getSha256Str(fbpd.getNewPwd()));
                break;
            }
            case 1:
            {
                Optional<StudentEntity> student = studentRepository.findById(fbpd.getId());
                if(!student.isPresent())
                {
                    return "未找到用户信息";
                }
                studentRepository.updatePwd(fbpd.getId(),EncryptSha256Util.getSha256Str(fbpd.getNewPwd()));
                break;
            }
            case 2:
            {
                Optional<TeacherEntity> teacher = teacherRepository.findById(fbpd.getId());
                if(!teacher.isPresent())
                {
                    return "未找到用户信息";
                }
                teacherRepository.updatePwd(fbpd.getId(),EncryptSha256Util.getSha256Str(fbpd.getNewPwd()));
                break;
            }
            default:
            {
                return "错误用户类型";
            }
        }
        return "密码修改成功";
    }
}
