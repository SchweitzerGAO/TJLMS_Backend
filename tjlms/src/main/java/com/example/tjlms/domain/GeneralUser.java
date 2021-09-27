package com.example.tjlms.domain;

// class GeneralUser
public class GeneralUser {
    private String id;
    private String emailAddress;
    private String name;

    public GeneralUser(String id, String emailAddress, String name) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// class User
class User extends GeneralUser
{
    private boolean verified;

    public User(String id, String emailAddress, String name, boolean verified) {
        super(id, emailAddress, name);
        this.verified = verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}

// class Admin
class Admin extends GeneralUser
{
    private String telNum;

    public Admin(String id, String emailAddress, String name, String telNum) {
        super(id, emailAddress, name);
        this.telNum = telNum;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}

