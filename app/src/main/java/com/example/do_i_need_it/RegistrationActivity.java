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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    //creating variables
    private EditText mEmail, mPass, mPhone, mName;
    private Button btnReg;
    private TextView mLogInHere;

    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //initialize the mAuth variable
        mAuth = FirebaseAuth.getInstance();

        mLogInHere = (TextView) findViewById(R.id.log_in_here);
        mLogInHere.setOnClickListener(this);

        btnReg = (Button) findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(this);

        mName = (EditText) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.phone);
        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.password);

        //registration();
    }


    //onCLick Method for all the Button in the Activity
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.log_in_here:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_reg:
                registerUser();
                break;

    }

//    private void  registration() {
//        mName = (EditText) findViewById(R.id.name_reg);
//        mPhone = (EditText) findViewById(R.id.phone_reg);
//        mEmail = (EditText) findViewById(R.id.email_reg);
//        mPass = (EditText) findViewById(R.id.password_reg);
//        btnReg = (Button) findViewById(R.id.btn_reg);
//        mLogInHere = (TextView) findViewById(R.id.log_in_here);


        //create account btn
//        btnReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = mName.getText().toString().trim();
//                String phone = mPhone.getText().toString().trim();
//                String email = mEmail.getText().toString().trim();
//                String password = mPass.getText().toString().trim();
//
//                if (TextUtils.isEmpty(name)){
//                    mName.setError("Name is Required..");
//                    mEmail.requestFocus();
//                    return;
//                }
//                if (TextUtils.isEmpty(phone)){
//                    mPhone.setError("Phone is Required..");
//                    mEmail.requestFocus();
//                    return;
//                }
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                    mEmail.setError("Please provide a valid email!");
//                    mEmail.requestFocus();
//                    return;
//                }
//                if (TextUtils.isEmpty(password)){
//                    mPass.setError("Password is Required..");
//                    mEmail.requestFocus();
//                    return;
//                }
//            }
//        });

        //Log In Here
//        mLogInHere.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            }
//        });
    }

    private void registerUser() {
        String name = mName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String password = mPass.getText().toString().trim();

        //validations
        if (name.isEmpty()){
            mName.setError("Name is Required!");
            mName.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            mPhone.setError("Phone is Required!");
            mPhone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please provide a valid email!");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            mPass.setError("Password is Required!");
            mPass.requestFocus();
            return;
        }

        if(password.length() <6){
            mPass.setError("Min password length should be 6 characters!");
            mPass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){
                           //create user object
                           User user = new User(name, phone, email);

                           FirebaseDatabase.getInstance().getReference("Users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(RegistrationActivity.this, "User has been Successfully Created", Toast.LENGTH_LONG).show();
                                       //redirect to login screen
                                       startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                   }
                                   else
                                   {
                                       Toast.makeText(RegistrationActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                   }
                               }
                           });
                       }else
                       {
                           Toast.makeText(RegistrationActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                       }
                    }
                });
    }
}