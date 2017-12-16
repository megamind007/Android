package com.sakib.servertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class profile extends AppCompatActivity {

    String json_string,target_string;

    TextView tv,logo;
    static int count=0;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv=(TextView)findViewById(R.id.tvWelcome);
        logo=(TextView)findViewById(R.id.logo);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font.otf");
        logo.setTypeface(typeface);
        new backgroundTask().execute();
        userLocalStore=new UserLocalStore(this);
    }


    public void readdata(View view){


        if(target_string==null){
            Toast.makeText(getApplicationContext(),"first get the json",Toast.LENGTH_LONG).show();
        }
        else{
            Intent i=new Intent(profile.this,ReadData.class);
            i.putExtra("json_data",target_string);
            startActivity(i);
        }
    }
    class backgroundTask extends AsyncTask<Void ,Void, String> {


        String json_url;

        @Override
        protected void onPreExecute() {
            json_url="http://chowdhurysakib.info/android/json_get_data.php";



        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStreamReader=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStreamReader));
                StringBuilder stringBuilder=new StringBuilder();
                while ((json_string=bufferedReader.readLine())!=null){

                    stringBuilder.append(json_string + "\n");
                    bufferedReader.close();
                    inputStreamReader.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            target_string=result;
            //tv.setText(result);
        }
    }


    public void goToCart(View view){
        ArrayList<String> arrayList=new ArrayList<>();

        if(count!=0) {
            ItemDetails itemDetails = new ItemDetails();
            arrayList = itemDetails.item;

        }
        Intent i=new Intent(profile.this,Cart.class);

        i.putExtra("list",arrayList);

        startActivity(i);
    }

    public  void goToLog(View view){
        Intent i=new Intent(profile.this,Log.class);
        startActivity(i);
    }
    public void goToPayment(View view){
        Intent i=new Intent(profile.this,Payment.class);
        startActivity(i);
    }
    public  void logout(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(authenticate()==false){
            
        }
    }
    public boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }
}
