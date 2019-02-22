package com.example.stanislavlukanov.right_form_of_registry;

import android.support.v4.app.Fragment;

public class AuthActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return Auth_Fragment.newInstance();
    }
}
