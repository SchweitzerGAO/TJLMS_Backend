package edu.tongji.tjlms.domain;
/**
 * @author Charles Gao
 * @description GeneralUser class
 * @date 2021/9/28
 */
// class GeneralUser
public class GeneralUser {
    private String id;
    private String name;
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




