package com.example.mhdzam.sellgateproject.DataModel;

/**
 * Created by MhdZam on 1/21/2018.
 */

public class NewUser {
    String ID;
    String GSM;
    String UserName;
    String FullName;
    String Email;
    String CreateDate;

    public NewUser(String ID, String GSM, String userName, String fullName, String email, String createDate) {
        this.ID = ID;
        this.GSM = GSM;
        UserName = userName;
        FullName = fullName;
        Email = email;
        CreateDate = createDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
}
