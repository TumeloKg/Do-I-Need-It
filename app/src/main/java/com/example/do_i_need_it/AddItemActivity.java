package com.example.do_i_need_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {

    //creating variables
     EditText mTitle, mPrice, mWebsite, mLocation;
     Button btn_add_new_item;

    DatabaseReference ref;
    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mTitle = (EditText) findViewById(R.id.title);
        mPrice = (EditText) findViewById(R.id.price);
        mWebsite = (EditText) findViewById(R.id.website);
        mLocation = (EditText) findViewById(R.id.location);
        btn_add_new_item = (Button) findViewById(R.id.btn_add_new_item);


        ref = FirebaseDatabase.getInstance().getReference().child("Items");

        btn_add_new_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertItemsData();
            }
        });

    }

    private void insertItemsData() {
        String title = mTitle.getText().toString();
        String price = mPrice.getText().toString();
        String website = mWebsite.getText().toString();
        String location = mLocation.getText().toString();

        Items items = new Items(title, price, website, location);

        ref.push().setValue(items);
        Toast.makeText(AddItemActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
        startActivity(new Intent( AddItemActivity.this, HomeActivity.class));
    }
}
