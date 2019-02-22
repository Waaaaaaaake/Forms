package com.example.stanislavlukanov.right_form_of_registry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile_activity extends AppCompatActivity{

    public static String USER_KEY="EMAIL_KEY";

    private AppCompatImageView mPhoto;
    private TextView mLogin;
    private TextView mPassword;
    private View.OnClickListener mPhotoClickListener = new View.OnClickListener() { // обработчик нажатия действия на фотографию
        @Override
        public void onClick(View v) {
            // что происходит когда нажимаем на фотографию
        }
    };

    @Override
    protected void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        setContentView(R.layout.ac_profile);


        mPhoto = findViewById(R.id.tv_photo);
        mLogin = findViewById(R.id.tv_email);
        mPassword = findViewById(R.id.tv_password);


        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.get(USER_KEY);

        mLogin.setText(user.getmLogin());
        mPassword.setText(user.getmPassword());

        mPhoto.setOnClickListener(mPhotoClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(this, AuthActivity.class));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
