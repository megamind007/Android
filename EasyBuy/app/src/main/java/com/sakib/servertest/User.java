package com.sakib.servertest;

/**
 * Created by sakib on 29-Mar-16.
 */
public class User {

    String username;
    String password;
    String shopname;
    String email;
    String address;
    String contact;


    public  User(String name,String password,String shopname,String email,String address,String contact){

        this.username=name;
        this.password=password;
        this.shopname=shopname;
        this.email=email;
        this.address=address;
        this.contact=contact;

    }

    public User(String username,String password){
        this.username=username;
        this.password=password;
        shopname="";
        contact="";
        email="";
        address="";
    }


}
