package edu.tongji.tjlms.dto;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class LoginDto(used in API login)
 */
public class LoginDto {
    private String emailAddress;
    private String password;
    private int userType;  // 0->Admin 1->Student 2->Teacher

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

