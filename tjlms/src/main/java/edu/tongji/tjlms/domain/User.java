package edu.tongji.tjlms.domain;
/**
 * @author Charles Gao
 * @date 2021/10/5
 * @description class User(direct base class for Student and Teacher)
 */
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
