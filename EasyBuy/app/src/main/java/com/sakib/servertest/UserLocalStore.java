package com.sakib.servertest;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sakib on 29-Mar-16.
 */
public class UserLocalStore {

    Context context;


    public final String  SP_Name="mySp";
    SharedPreferences sp;

    public UserLocalStore(Context ctx){

        sp=context.getSharedPreferences(SP_Name,0);

    }

    public void storeUserData(User user){
        SharedPreferences.Editor SP_Editor=sp.edit();
        SP_Editor.putString("username",user.username);
        SP_Editor.putString("password",user.password);
        SP_Editor.putString("shopname",user.shopname);
        SP_Editor.putString("email",user.email);
        SP_Editor.putString("address",user.address);
        SP_Editor.putString("contact",user.contact);
        SP_Editor.commit();


    }

    public User getLoggedInUser(){
        String username=sp.getString("username", "");
        String password=sp.getString("password","");
        String shopname=sp.getString("shopname","");
        String email=sp.getString("email","");
        String address=sp.getString("address","");
        String contact=sp.getString("contact","");

        User storedUser=new User(username,password,shopname,email,address,contact);

        return storedUser;
    }


    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor SP_Editor=sp.edit();
        SP_Editor.putBoolean("loggedIn",loggedIn);
        SP_Editor.commit();
    }

    public  boolean getUserLoggedIn(){
        if(sp.getBoolean("loggedIn",false)==true){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor SP_Editor=sp.edit();
        SP_Editor.clear();
        SP_Editor.commit();
    }

}
