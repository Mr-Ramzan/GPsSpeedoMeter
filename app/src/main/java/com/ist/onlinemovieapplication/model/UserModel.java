package com.ist.onlinemovieapplication.model;

public class UserModel {

    private String userName;
    private String userAge;
    private String userEmail;
    private String userPhone;
    private String userPassword;

    public UserModel() {
    }

    public UserModel(String userName, String userAge, String userEmail, String userPhone, String userPassword) {
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
