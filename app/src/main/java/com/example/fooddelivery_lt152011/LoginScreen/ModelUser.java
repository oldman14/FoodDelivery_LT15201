package com.example.fooddelivery_lt152011.LoginScreen;

public class ModelUser {
    private int UserID;
    private String UserName;
    private int UserPhone;
    private String UserMail;
    private String UserBirthday;
    private String UserImage;
    private String Token;

    public ModelUser(int userID, String userName, int userPhone, String userMail, String userBirthday, String userImage, String token) {
        UserID = userID;
        UserName = userName;
        UserPhone = userPhone;
        UserMail = userMail;
        UserBirthday = userBirthday;
        UserImage = userImage;
        Token = token;
    }

    public ModelUser() {

    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public int getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(int userPhone) {
        UserPhone = userPhone;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getUserBirthday() {
        return UserBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        UserBirthday = userBirthday;
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

    public void setUserName(String string) {
    }
}
