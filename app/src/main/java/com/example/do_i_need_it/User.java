package com.example.do_i_need_it;

public class User {

    public  String name, phone, email;

    //empty constructor
    public User(){
    }

    //constructor to accept arguments
    public User(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
