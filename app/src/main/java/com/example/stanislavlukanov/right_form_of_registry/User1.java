package com.example.stanislavlukanov.right_form_of_registry;

import java.io.Serializable;

public class User1 implements Serializable {

    private String mLogin;

    public User1(String mLogin) {
        this.mLogin = mLogin;

    }

    public String getmLogin() {

        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }
}