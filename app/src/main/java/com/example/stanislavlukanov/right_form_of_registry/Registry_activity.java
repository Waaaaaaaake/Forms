package com.example.stanislavlukanov.right_form_of_registry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Registry_activity extends AppCompatActivity {

    public static String REGISTRATION_KEY="EMAIL_KEY";
    private TextView mLogin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_activity);
        mLogin=findViewById(R.id.tv_form);

        Bundle bundle = getIntent().getExtras();

        User1 user = (User1) bundle.get(REGISTRATION_KEY);
        mLogin.setText(user.getmLogin());


    }
}
