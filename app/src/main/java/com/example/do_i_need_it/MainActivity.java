package com.example.do_i_need_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmail, mPass;
    private Button btnLogin;
    private TextView mForgetPassword, mRegisterHere;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        switch (view.getId()){
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

        if(email.isEmpty()){
            mEmail.setError("Email is required!");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please Enter a valid email!");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            mPass.setError("Password is required!");
            mPass.requestFocus();
            return;
        }
        if (password.length() < 6){
            mPass.setError("Min password length is 6 characters!");
            mPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //redirect to home page
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private void loginDetails()
//    {
//        mEmail = findViewById(R.id.email);
//        mPass = findViewById(R.id.password);
//        btnLogin = findViewById(R.id.btn_login);
//        mForgetPassword = findViewById(R.id.forget_password);
//        mRegisterHere = findViewById(R.id.register_here);
//
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = mEmail.getText().toString().trim();
//                String pass = mPass.getText().toString().trim();
//
//                if (TextUtils.isEmpty(email)) {
//                    mEmail.setError("Email Required..");
//                    return;
//                }
//                if (TextUtils.isEmpty(pass)) {
//                    mPass.setError("Password Required..");
//                    return;
//                }
//            }
//        });
//
//        //Registration
//        mRegisterHere.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
//            }
//        });
//
//        //Reset Password
//        mForgetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
//            }
//        });
//    }
}