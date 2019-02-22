package com.example.stanislavlukanov.right_form_of_registry;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Auth_Fragment extends Fragment {


    private EditText mLogin;   // Создание TextView для связи с xml элементом
    private EditText mPassword; // Создание TextView для связи с xml элементом
    private Button mEnter;  // Создание Button для связи с xml элементом
    private Button mRegistry;  // Создание Button для связи с xml элементом
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static Auth_Fragment newInstance() {

        Bundle args = new Bundle();

        Auth_Fragment fragment = new Auth_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {   //обработчик кнопки ВОЙТИ
        @Override
        public void onClick(View view) {
            boolean isLoginSuccess = false;
            for(User user : mSharedPreferencesHelper.getUsers()){
                if(user.getmLogin().equalsIgnoreCase(mLogin.getText().toString()) && user.getmPassword().equals(mPassword.getText().toString())){
                    isLoginSuccess = true;
                    if (isEmailValid() && isPasswordValid()){
                        Intent startProfileIntent =
                                new Intent  (getActivity(), Profile_activity.class);
                        startProfileIntent.putExtra(Profile_activity.USER_KEY,new User(mLogin.getText().toString(), mPassword.getText().toString()));
                        startActivity(startProfileIntent);
                    } else {
                        showMessage(R.string.input_error);
                    }
                    break;
                }
            }
            if (!isLoginSuccess ) {
                showMessage(R.string.login_input_error);
            }
        }
    };
    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {  // обработчик кнопки ЗАРЕГИСТРИРОВАТЬСЯ
       /*  @Override
        public void onClick(View view) {  //todo Обработка нажатия по кнопке register
            if (isEmailValid() && isPasswordValid()) {
                Intent startRegistrationIntent =
                        new Intent(getActivity(), Registry_activity.class);
                startRegistrationIntent.putExtra(Registry_activity.REGISTRATION_KEY,new User1(mLogin.getText().toString()));
                startActivity(startRegistrationIntent);
            }
            else {
                showMessage(R.string.login_input_error);
            }
        }
*/
        @Override
        public void onClick(View v) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,RegistrationFragment.newInstance())
                    .addToBackStack(RegistrationFragment.class.getName())
                    .commit();
        }
    };

    private boolean isEmailValid(){    // метод для логики поля LOGIN
        return !TextUtils.isEmpty(mLogin.getText())
                    && Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
    };
    private boolean isPasswordValid(){  // метод для логики поля PASSWORD
        return !TextUtils.isEmpty(mPassword.getText());
    };

    private void showMessage(@StringRes int string) {   // метод для показа уведомления (можно использовать как дефолтный и просто вызывать по несколько раз)
        Toast.makeText(getActivity(), string,Toast.LENGTH_LONG).show();
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fr_auth, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());


        mLogin=v.findViewById(R.id.etLogin);  //присваиваем в Text View для LOGiN связанный xml элемент (поле ввода) по id
        mPassword=v.findViewById(R.id.etPassword);  //присваиваем в Text View для PASSWORD связанный xml элемент (поле ввода) по id
        mEnter=v.findViewById(R.id.button_enter);   //присваиваем в Button для кнопки ВОЙТИ связанный xml элемент (кнопку) по id
        mRegistry=v.findViewById(R.id.button_registry);  //присваиваем в Button для кнопки ЗАРЕГИСТРИРОВАТЬСЯ связанный xml элемент (кнопку) по id


        mEnter.setOnClickListener(mOnEnterClickListener);   // навешиваем на кнопку Button ВОЙТИ логику / обработчик / созданный метод
        mRegistry.setOnClickListener(mOnRegisterClickListener);  // навешиваем на кнопку Button ЗАРЕГИСТРИРОВАТЬСЯ логику / обработчик / созданный метод
        return v;

    }
}
