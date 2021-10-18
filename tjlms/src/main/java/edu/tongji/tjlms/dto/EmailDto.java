package edu.tongji.tjlms.dto;

/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class EmailDto(used in sending email API)
 */
public class EmailDto {
    private String id;
    private String emailAddress;
    private int userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
