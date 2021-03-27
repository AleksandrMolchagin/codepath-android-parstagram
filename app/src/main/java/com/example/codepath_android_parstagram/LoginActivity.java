  package com.example.codepath_android_parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity" ;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super. onCreate ( savedInstanceState ) ;
        setContentView(R. layout.activity_login);
        if (ParseUser.getCurrentUser() != null) { goMainActivity();}
        etUsername = findViewById(R.id.UsernameTxt);
        etPassword = findViewById(R.id.PasswordTxt) ;
        btnLogin = findViewById(R.id.LoginBtn) ;
        btnSignup = findViewById(R.id.signupBtn);
        btnLogin. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button") ;
                String username = etUsername.getText() .toString();
                String password = etPassword. getText() .toString();
                loginUser(username, password) ;

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                Log.i(TAG, "onClick sign up button") ;
                String username = etUsername.getText().toString();
                String password = etPassword. getText().toString();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Issue with sign up", e);
                            Toast.makeText(LoginActivity.this, "Issue with sign up:\n" + e, Toast.LENGTH_LONG).show();                        } else {
                        }
                        loginUser(username, password) ;
                    }
                });
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG,"Attempting to login user " + username) ;
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with Login:\n" + e, Toast.LENGTH_LONG).show();
                    return;
                }
                // TODO: navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}