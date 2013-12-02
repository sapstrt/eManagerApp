package com.sapstrt.emanager.domain;

/**
 * Created by pteltu on 11/28/13.
 */
public class User {
    private Integer userId;
    private String emailId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
