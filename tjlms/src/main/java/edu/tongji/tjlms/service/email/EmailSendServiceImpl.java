package edu.tongji.tjlms.service.email;

import edu.tongji.tjlms.dto.EmailDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the email sending service implementation
 */
@Service
public class EmailSendServiceImpl implements EmailSendService {
    @Autowired
    private JavaMailSender jms;

    // the sender value is in ./resources/application.properties
    @Value("${spring.mail.username}")
    private String sender;
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;
    private static String verificationCode = "";
    // role "ADMIN" is always verified

    public static String getVerificationCode() {
        return verificationCode;
    }

    public static void setVerificationCodeToEmpty() {
        verificationCode = "";
    }

    @Override
    public String sendEmail(EmailDto ed) {
            // generate verification code
            verificationCode = VerificationCodeUtil.generateCode();
            // query the information from the DBMS
            String id = ed.getId();
            String name = "";
            int userType = ed.getUserType();
            switch (userType)
            {
                // student
                case 1:
                {
                    // JPA code
                    Optional<StudentEntity> student = studentRepository.findById(id);
                    if(!student.isPresent())
                    {
                        return "???????????????";
                    }
                    name = student.get().getName();
                    break;
                }
                // teacher
                case 2:
                {
                    // JPA code
                    Optional<TeacherEntity> teacher = teacherRepository.findById(id);
                    if(!teacher.isPresent())
                    {
                        return "???????????????";
                    }
                    name = teacher.get().getName();
                    break;
                }
            }
            // set the email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);  // the sender
            message.setTo(ed.getEmailAddress());  // the receiver
            message.setSubject("TJLMS????????????");  // subject
            // the text
            final String text = "?????????????????????"+ verificationCode+"\n???????????????????????????????????????????????????????????????????????????????????????????????????"+"\nTJLMS??????????????????";
            String head = name+((userType == 1) ? "?????????\n" :"?????????\n")+"?????????\n";
            String mainMsg = head+text;
            message.setText(mainMsg);
            // send it!
            jms.send(message);
            return "????????????";

    }
}
