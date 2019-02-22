package com.example.stanislavlukanov.right_form_of_registry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class RegistrationFragment extends Fragment {

    private EditText mLogin;   // Создание TextView для связи с xml элементом
    private EditText mPassword; // Создание TextView для связи с xml элементом
    private EditText mPasswordAgain;  // Создание TextView для связи с xml элементом
    private Button mRegistration;  // Создание Button для связи с xml элементом
    private SharedPreferencesHelper  mSharedPreferencesHelper;


    public static RegistrationFragment newInstance(){
        return new RegistrationFragment();
    }

    private View.OnClickListener mOnRegistrationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isInputValid()){
                boolean isAdded = mSharedPreferencesHelper.addUser (new User(
                  mLogin.getText().toString(),
                  mPassword.getText().toString()
                ));

                if(isAdded){
                    showMessage(R.string.success);
                    getFragmentManager().popBackStack();
                }   else {
                    showMessage(R.string.login_error);
                }

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_registration,container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        mLogin = view.findViewById(R.id.etLogin);
        mPassword = view.findViewById(R.id.etPassword);
        mPasswordAgain = view.findViewById(R.id.tvPasswordAgain);
        mRegistration = view.findViewById(R.id.btnRegistration);


        mRegistration.setOnClickListener(mOnRegistrationClickListener);

        return view;
    }


    public boolean isInputValid(){
        String email = mLogin.getText().toString();
        if(isEmailValid(email) && isPasswordValid()){
            return true;
        }
        return false;
    }

    private boolean isEmailValid(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(){
        String password = mPassword.getText().toString();
        String passwordAgain = mPasswordAgain.getText().toString();

        return password.equals(passwordAgain)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(passwordAgain);
    }

    private void showMessage (@StringRes int string){
        Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT).show();
    }

}
