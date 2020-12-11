package com.example.do_i_need_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText mEmail, mPass;
    private Button btnLogin;
    private TextView mForgetPassword, mRegisterHere;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRegisterHere = (TextView) findViewById(R.id.register_here);
        mRegisterHere.setOnClickListener(this);

        mForgetPassword = (TextView) findViewById(R.id.forget_password);
        mForgetPassword.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.password);

        //initialize firebase mAuth
        mAuth = FirebaseAuth.getInstance();


        // loginDetails();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_here:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = mEmail.getText().toString().trim();
        String password = mPass.getText().toString().trim();

        if (email.isEmpty()) {
            mEmail.setError("Email is required!");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please Enter a valid email!");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mPass.setError("Password is required!");
            mPass.requestFocus();
            return;
        }
        if (password.length() < 6) {
            mPass.setError("Min password length is 6 characters!");
            mPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //redirect to home page
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}