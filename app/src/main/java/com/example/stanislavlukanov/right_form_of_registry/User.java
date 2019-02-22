package com.example.stanislavlukanov.right_form_of_registry;

import java.io.Serializable;

public class User implements Serializable{

    private String mLogin;
    private String mPassword;

    public User(String mLogin, String mPassword) {
        this.mLogin = mLogin;
        this.mPassword = mPassword;

    }

    public String getmPassword() {

        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmLogin() {

        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }
}

