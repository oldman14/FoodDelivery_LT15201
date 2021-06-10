package com.example.fooddelivery_lt152011.LoginScreen;

public class ModelUser {
    private int UserID;
    private String UserName;
    private int UserPhone;
    private String AddressID;
    private String UserMail;
    private String UserBirthDay;
    private String UserImage;
    private String Token;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(int userPhone) {
        UserPhone = userPhone;
    }

    public String getAddressID() {
        return AddressID;
    }

    public void setAddressID(String addressID) {
        AddressID = addressID;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getUserBirthDay() {
        return UserBirthDay;
    }

    public void setUserBirthDay(String userBirthDay) {
        UserBirthDay = userBirthDay;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public ModelUser() {
    }

    public ModelUser(int userID, String userName, int userPhone, String addressID, String userMail, String userBirthDay, String userImage, String token) {
        UserID = userID;
        UserName = userName;
        UserPhone = userPhone;
        AddressID = addressID;
        UserMail = userMail;
        UserBirthDay = userBirthDay;
        UserImage = userImage;
        Token = token;
    }
}
