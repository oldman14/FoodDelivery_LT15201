package com.example.shipper_lt15201.ModelUser;

public class ModelUser {
    private   int UserID;
    private String UserName;
    private String   UserPhone;
    private String UserMail;
    private String UserBirthday;
    private String UserImage;
    private String Token;

    public ModelUser(int userID, String userName, String userPhone, String userMail, String userBirthday, String userImage, String token) {
        UserID = userID;
        UserName = userName;
        UserPhone = userPhone;
        UserMail = userMail;
        UserBirthday = userBirthday;
        UserImage = userImage;
        Token = token;
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

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
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
}
