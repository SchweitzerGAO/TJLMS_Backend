package com.example.tjlms.domain;

public class User extends GeneralUser
{
    private boolean verified;


    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
